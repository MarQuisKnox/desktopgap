package org.smartly.application.desktopgap.impl.app_system;

import org.smartly.commons.io.repository.deploy.FileDeployer;
import org.smartly.commons.lang.compilers.CompilerRegistry;
import org.smartly.commons.lang.compilers.ICompiler;
import org.smartly.commons.logging.Level;
import org.smartly.commons.util.FormatUtils;
import org.smartly.commons.util.PathUtils;
import org.smartly.packages.htmldeployer.impl.compilers.CompilerLess;
import org.smartly.packages.velocity.impl.compilers.CompilerVelocity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DeployerAppSystem extends FileDeployer {

    public final Map<String, Object> _context;

    public DeployerAppSystem(final String targetFolder,
                             final boolean silent) {
        super("", targetFolder,
                silent, false, false, false);
        super.setOverwrite(false);

        _context = new HashMap<String, Object>();

        this.init();
    }

    @Override
    public byte[] compile(final byte[] data, final String filename) {
        try {
            final String ext = PathUtils.getFilenameExtension(filename, true);
            final ICompiler compiler = CompilerRegistry.get(ext);
            if (null != compiler) {
                _context.put(CompilerVelocity.ARG_FILE, filename);
                return compiler.compile(data, _context);
            } else {
                super.getLogger().log(Level.WARNING,
                        FormatUtils.format("COMPILER NOT FOUND FOR '{0}'", filename));
            }
        } catch (Throwable t) {
            super.getLogger().log(Level.SEVERE,
                    FormatUtils.format("ERROR COMPILING '{0}': {1}", filename, t), t);
        }
        return null;
    }

    @Override
    public byte[] compress(final byte[] data, final String filename) {
        return null;
    }

    // ------------------------------------------------------------------------
    //                      p r i v a t e
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    //                      p r i v a t e
    // ------------------------------------------------------------------------

    private void init() {
        // pre-process
        FileDeployer.getPreProcessorFiles().add(".less");
        FileDeployer.getPreProcessorFiles().add(".js");
        FileDeployer.getPreProcessorFiles().add(".css");
        FileDeployer.getPreProcessorFiles().add(".html");
        // compile
        FileDeployer.getCompileFiles().put(".less", ".css");    // less-js compiler
        FileDeployer.getCompileFiles().put(".js", ".js");       // closure compiler
        FileDeployer.getCompileFiles().put(".html", ".html");   // template compiler
        // compress
        FileDeployer.getCompressFiles().add(".js");
        FileDeployer.getCompressFiles().add(".css");

        //-- add compilers --//
        CompilerRegistry.register(".less", CompilerLess.class);
        CompilerRegistry.register(".html", CompilerVelocity.class);
    }

}