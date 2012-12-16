package org.smartly.application.desktopgap.impl.resources;

import org.smartly.application.desktopgap.impl.app.IDesktopConstants;
import org.smartly.application.desktopgap.impl.app.applications.window.AppManifest;
import org.smartly.commons.io.repository.deploy.FileDeployer;
import org.smartly.commons.util.ClassLoaderUtils;
import org.smartly.commons.util.PathUtils;

import java.io.InputStream;

/**
 *
 */
public final class AppResources {

    private static final String PATH_FRAMES = "/frames";
    private static final String PATH_APP_TEMPLATE = "/app_template";
    private static final String PATH_APP_FRAME = "/app_frame";
    private static final String PATH_BLANK = "/blank";

    private static final String PRE_INDEX_PAGE = IDesktopConstants.PRE_INDEX_PAGE;

    private AppResources() {
    }

    public InputStream getResourceAsStream(final String resourceName) {
        return ClassLoaderUtils.getResourceAsStream(resourceName);
    }

    public String toExternalForm(final String resourceName) {
        return ClassLoaderUtils.getResource(resourceName).toExternalForm();
    }

    // ------------------------------------------------------------------------
    //                      p r i v a t e
    // ------------------------------------------------------------------------

    // --------------------------------------------------------------------
    //                      S T A T I C
    // --------------------------------------------------------------------

    private static final String _root = PathUtils.getPackagePath(AppResources.class);

    private static AppResources __instance;

    private static AppResources getInstance() {
        if (null == __instance) {
            __instance = new AppResources();
        }
        return __instance;
    }

    //-- PAGES --//

    public static String getPageUri_BLANK() {
        final String path = PathUtils.concat(PATH_BLANK, "blank.html");
        return getInstance().toExternalForm(PathUtils.concat(_root, path));
    }

    //-- TEMPLATE --//

    public static String getAppTemplateUri(final String resource) {
        final String path = PathUtils.concat(PATH_APP_TEMPLATE, resource);
        return getInstance().toExternalForm(PathUtils.concat(_root, path));
    }

    public static InputStream getAppTemplateIcon() {
        final String path = PathUtils.concat(PATH_APP_TEMPLATE, "/icon.png");
        return getInstance().getResourceAsStream(PathUtils.concat(_root, path));
    }

    //-- FX WINDOW FRAME --//

    public static String getAppFrameUri(final String resource) {
        final String path = PathUtils.concat(PATH_APP_FRAME, resource);
        return getInstance().toExternalForm(PathUtils.concat(_root, path));
    }

    //-- DEPLOYERS --//

    public static void deploy_FRAMES(final AppManifest manifest,
                                     final String target) {
        final String index = manifest.getIndex(); // used to customize run page
        final String frame = manifest.getFrame();
        final String source = PathUtils.concat(PATH_FRAMES, frame);
        final FileDeployer deployer = new FileDeployer(source, target, true, false, false, false) {
            @Override
            public byte[] compress(byte[] data, String filename) {
                return null;
            }

            @Override
            public byte[] compile(byte[] data, String filename) {
                return null;
            }
        };
        deployer.setOverwrite(true);
        deployer.getSettings().clear();
        // exclude
        deployer.getSettings().getExcludeFiles().add(".class");
        // pre-process
        deployer.getSettings().getPreProcessorFiles().add(".html");
        deployer.getSettings().getPreprocessorValues().put(PRE_INDEX_PAGE, index);
        deployer.deployChildren();
    }
}
