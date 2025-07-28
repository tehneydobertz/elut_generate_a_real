import java.util.HashMap;
import java.util.Map;

/**
 * Real-time Automation Script Tracker API
 *
 * This API provides a way to track the execution of automation scripts in real-time.
 * It allows for registering scripts, updating their status, and retrieving their execution history.
 *
 * @author [Your Name]
 */
public class ElutGenerateAReal {

    // Script execution status
    public enum ScriptStatus {
        PENDING, RUNNING, COMPLETE, FAILED
    }

    // Map to store script execution history
    private static Map<String, ScriptExecutionHistory> scriptHistory = new HashMap<>();

    /**
     * Registers a new script with the tracker
     *
     * @param scriptId unique identifier for the script
     * @param scriptName name of the script
     */
    public static void registerScript(String scriptId, String scriptName) {
        scriptHistory.put(scriptId, new ScriptExecutionHistory(scriptName));
    }

    /**
     * Updates the status of a running script
     *
     * @param scriptId unique identifier for the script
     * @param status new status of the script
     */
    public static void updateScriptStatus(String scriptId, ScriptStatus status) {
        scriptHistory.get(scriptId).updateStatus(status);
    }

    /**
     * Retrieves the execution history of a script
     *
     * @param scriptId unique identifier for the script
     * @return script execution history
     */
    public static ScriptExecutionHistory getScriptHistory(String scriptId) {
        return scriptHistory.get(scriptId);
    }

    /**
     * Inner class to represent script execution history
     */
    public static class ScriptExecutionHistory {
        private String scriptName;
        private ScriptStatus status;
        private long startTime;
        private long endTime;

        public ScriptExecutionHistory(String scriptName) {
            this.scriptName = scriptName;
            this.status = ScriptStatus.PENDING;
        }

        public void updateStatus(ScriptStatus status) {
            this.status = status;
            if (status == ScriptStatus.RUNNING) {
                this.startTime = System.currentTimeMillis();
            } else if (status == ScriptStatus.COMPLETE || status == ScriptStatus.FAILED) {
                this.endTime = System.currentTimeMillis();
            }
        }

        public String getScriptName() {
            return scriptName;
        }

        public ScriptStatus getStatus() {
            return status;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }
    }
}