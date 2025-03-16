/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.gda.system;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;

/**
 * Shell representation of class for student implementation.
 */
public class SystemCpuUtilTask extends BaseSystemUtilTask {
    // Logger instance (opcional, por si quieres loggear información)
    private static final Logger _Logger = Logger.getLogger(SystemCpuUtilTask.class.getName());

    // Constructor
    public SystemCpuUtilTask() {
        super(ConfigConst.NOT_SET, ConfigConst.DEFAULT_TYPE_ID);
    }

    @Override
    public float getTelemetryValue() {
        OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();
        double cpuUtil = mxBean.getSystemLoadAverage();

        return (float) cpuUtil;
    }
}
