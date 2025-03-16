package programmingtheiot.gda.system;

import java.util.logging.Logger;
import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;

/**
 * Shell representation of class for student implementation.
 */
public class SystemPerformanceManager {
    private static final Logger _Logger = Logger.getLogger(SystemPerformanceManager.class.getName());
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
    }

    // Public methods
    public void handleTelemetry() {
        // Implement telemetry logic here
    }

    public void setDataMessageListener(IDataMessageListener listener) {
        // Set the data message listener
    }

    public boolean startManager() {
        _Logger.info("SystemPerformanceManager is starting...");
        return true;
    }

    public boolean stopManager() {
        _Logger.info("SystemPerformanceManager is stopped.");
        return true;
    }
}
