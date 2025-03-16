package programmingtheiot.gda.system;

import java.util.logging.Logger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;

/**
 * Shell representation of class for student implementation.
 */
public class SystemPerformanceManager {
    private static final Logger _Logger = Logger.getLogger(SystemPerformanceManager.class.getName());

    private ScheduledExecutorService schedExecSvc = null;
    private SystemCpuUtilTask sysCpuUtilTask = null;
    private SystemMemUtilTask sysMemUtilTask = null;
    private Runnable taskRunner = null;
    private boolean isStarted = false;
    private int pollRate = ConfigConst.DEFAULT_POLL_CYCLES;

    // Constructor
    public SystemPerformanceManager() {
        this.pollRate = ConfigUtil.getInstance().getInteger(
            ConfigConst.GATEWAY_DEVICE,
            ConfigConst.POLL_CYCLES_KEY,
            ConfigConst.DEFAULT_POLL_CYCLES
        );

        if (this.pollRate <= 0) {
            this.pollRate = ConfigConst.DEFAULT_POLL_CYCLES;
        }

        this.schedExecSvc = Executors.newScheduledThreadPool(1);
        this.sysCpuUtilTask = new SystemCpuUtilTask();
        this.sysMemUtilTask = new SystemMemUtilTask();

        this.taskRunner = () -> this.handleTelemetry();
    }

    // Public methods
    public void handleTelemetry() {
        float cpuUtil = this.sysCpuUtilTask.getTelemetryValue();
        float memUtil = this.sysMemUtilTask.getTelemetryValue();

        // NOTE: you may need to change the logging level to 'info' to see the message
        _Logger.fine("CPU utilization: " + cpuUtil + ", Mem utilization: " + memUtil);
    }

    public void setDataMessageListener(IDataMessageListener listener) {
        // Set the data message listener
    }

    public boolean startManager() {
        if (!this.isStarted) {
            _Logger.info("SystemPerformanceManager is starting...");

            ScheduledFuture<?> futureTask = this.schedExecSvc.scheduleAtFixedRate(
                this.taskRunner, 1L, this.pollRate, TimeUnit.SECONDS
            );

            this.isStarted = true;
        } else {
            _Logger.info("SystemPerformanceManager is already started.");
        }

        return this.isStarted;
    }

    public boolean stopManager() {
        this.schedExecSvc.shutdown();
        this.isStarted = false;

        _Logger.info("SystemPerformanceManager is stopped.");

        return true;
    }
}
