import { useState, useEffect } from 'react';

const Health = () => {
    const [status, setStatus] = useState("");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [lastChecked, setLastChecked] = useState(null);

    const fetchWebsiteStatus = async () => {
        try {
            setLoading(true);
            setError("");
            
            const response = await fetch('http://localhost:8080/api/content/HealthStatus');
            const healthStatus = await response.json();
            setStatus(healthStatus.status);
            setLastChecked(new Date());
        }
        catch (err) {
            console.error(err.message);
            setError("Failed to fetch status");
            setStatus("DOWN");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        fetchWebsiteStatus();
        
        // Auto-refresh every 30 seconds
        const interval = setInterval(fetchWebsiteStatus, 30000);
        return () => clearInterval(interval);
    }, []);

    const getStatusColor = () => {
        if (loading) return "bg-gray-100 text-gray-600";
        if (status === "UP") return "bg-green-50 text-green-800 border-green-200";
        return "bg-red-50 text-red-800 border-red-200";
    };

    const getStatusIcon = () => {
        if (loading) return "🔄";
        if (status === "UP") return "✅";
        return "❌";
    };

    const getStatusDot = () => {
        if (loading) return "bg-gray-400 animate-pulse";
        if (status === "UP") return "bg-green-500";
        return "bg-red-500";
    };

    return (
        <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
            <div className="max-w-md w-full">
                {/* Main Status Card */}
                <div className="bg-white rounded-xl shadow-lg border border-gray-200 overflow-hidden">
                    {/* Header */}
                    <div className="bg-gradient-to-r from-blue-600 to-blue-700 px-6 py-4">
                        <h1 className="text-xl font-semibold text-white flex items-center gap-3">
                            <div className="w-8 h-8 bg-white bg-opacity-20 rounded-lg flex items-center justify-center">
                                <div className="w-4 h-4 bg-white rounded-full"></div>
                            </div>
                            System Health Status
                        </h1>
                    </div>

                    {/* Status Content */}
                    <div className="p-6">
                        {/* Current Status */}
                        <div className={`rounded-lg border-2 p-4 mb-4 transition-all duration-300 ${getStatusColor()}`}>
                            <div className="flex items-center justify-between">
                                <div className="flex items-center gap-3">
                                    <div className={`w-3 h-3 rounded-full ${getStatusDot()}`}></div>
                                    <span className="text-lg font-semibold">
                                        {loading ? "Checking..." : `System is ${status}`}
                                    </span>
                                </div>
                                <span className="text-2xl">{getStatusIcon()}</span>
                            </div>
                        </div>

                        {/* Error Message */}
                        {error && (
                            <div className="bg-red-50 border border-red-200 rounded-lg p-3 mb-4">
                                <div className="flex items-center gap-2">
                                    <span className="text-red-500">⚠️</span>
                                    <span className="text-red-700 text-sm">{error}</span>
                                </div>
                            </div>
                        )}

                        {/* Status Details */}
                        <div className="space-y-3">
                            <div className="flex justify-between items-center text-sm text-gray-600">
                                <span>Last Checked:</span>
                                <span className="font-medium">
                                    {lastChecked ? lastChecked.toLocaleTimeString() : "Never"}
                                </span>
                            </div>
                            
                            <div className="flex justify-between items-center text-sm text-gray-600">
                                <span>Auto Refresh:</span>
                                <span className="font-medium">Every 30 seconds</span>
                            </div>
                        </div>

                        {/* Manual Refresh Button */}
                        <button
                            onClick={fetchWebsiteStatus}
                            disabled={loading}
                            className="w-full mt-6 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-300 text-white font-medium py-2.5 px-4 rounded-lg transition-colors duration-200 flex items-center justify-center gap-2"
                        >
                            {loading ? (
                                <>
                                    <div className="w-4 h-4 border-2 border-white border-t-transparent rounded-full animate-spin"></div>
                                    Checking...
                                </>
                            ) : (
                                <>
                                    🔄 Refresh Status
                                </>
                            )}
                        </button>
                    </div>
                </div>

                {/* Additional Info Card */}
                <div className="mt-4 bg-white rounded-lg shadow-sm border border-gray-200 p-4">
                    <h2 className="text-sm font-semibold text-gray-700 mb-2">Status Information</h2>
                    <div className="grid grid-cols-2 gap-4 text-xs text-gray-600">
                        <div className="flex items-center gap-2">
                            <div className="w-2 h-2 bg-green-500 rounded-full"></div>
                            <span>UP - Service Running</span>
                        </div>
                        <div className="flex items-center gap-2">
                            <div className="w-2 h-2 bg-red-500 rounded-full"></div>
                            <span>DOWN - Service Offline</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Health;