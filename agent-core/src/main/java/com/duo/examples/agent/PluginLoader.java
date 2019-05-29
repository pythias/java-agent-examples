package com.duo.examples.agent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pythias
 * @since 2019-05-29
 */
public class PluginLoader extends ClassLoader {
    private List<URL> pluginJars;
    private static PluginLoader INSTANCE;

    public static PluginLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginLoader("duo-agent", PluginBootstrap.class.getClassLoader());
        }

        return INSTANCE;
    }

    public PluginLoader(String name, ClassLoader parent) {
        super(name, parent);

        pluginJars = new ArrayList<>();
        try {
            String runDir = System.getProperty("RUN_DIR");
            if (runDir == null || runDir.isEmpty()) {
                runDir = "./bin";
            }
            pluginJars.add(new File(runDir + "/plugins/http-client-plugin-0.0.1.jar").toURI().toURL());
            pluginJars.add(new File(runDir + "/plugins/jedis-plugin-0.0.1.jar").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        for (URL url : pluginJars) {
            try {
                URL classUrl = new URL("jar:file:" + url.getFile() + "!/" + name.replace('.', '/') + ".class");
                byte[] data = getClassData(classUrl);
                return defineClass(name, data, 0, data.length);
            } catch (IOException ignore) {

            }
        }

        return super.findClass(name);
    }

    private byte[] getClassData(URL classUrl) throws IOException {
        InputStream is = classUrl.openStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int tmp;
        while ((tmp = is.read()) != -1) {
            os.write(tmp);
        }
        return os.toByteArray();
    }
}
