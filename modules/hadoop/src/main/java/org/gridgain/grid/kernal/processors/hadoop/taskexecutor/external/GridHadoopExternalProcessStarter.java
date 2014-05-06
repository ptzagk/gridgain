/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.hadoop.taskexecutor.external;

import org.gridgain.grid.kernal.processors.hadoop.taskexecutor.external.communication.*;
import org.gridgain.grid.logger.log4j.*;
import org.gridgain.grid.marshaller.optimized.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Hadoop external process base class.
 */
public class GridHadoopExternalProcessStarter {
    /**
     * Supported arguments parameters:
     * <li>
     *     <ul> -p Process implementation class name.
     * </li>
     *
     * @param args Process arguments.
     */
    public static void main(String[] args) {
        try {
            GridHadoopChildProcessBase process = createProcess(args);

            GridHadoopExternalCommunication comm;
            comm = new GridHadoopExternalCommunication(UUID.randomUUID(),
                new GridOptimizedMarshaller(), new GridLog4jLogger(), Executors.newFixedThreadPool(1), "test");

            comm.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO.
        }
        finally {

        }
    }

    /**
     * @param processArgs Process arguments.
     * @return Child process instance.
     */
    private static GridHadoopChildProcessBase createProcess(String[] processArgs) throws Exception {
        for (int i = 0; i < processArgs.length; i++) {
            String arg = processArgs[i];

            if ("-p".equals(arg)) {
                if (i == processArgs.length - 1)
                    throw new Exception("Missing process class name for '-p' parameter");

                String processClsName = processArgs[i + 1];

                Class<?> cls = Class.forName(processClsName);

                Constructor<?> ctor = cls.getConstructor(String[].class);

                return (GridHadoopChildProcessBase)ctor.newInstance(processArgs);
            }
        }

        throw new Exception("Failed to find process class name in arguments.");
    }
}
