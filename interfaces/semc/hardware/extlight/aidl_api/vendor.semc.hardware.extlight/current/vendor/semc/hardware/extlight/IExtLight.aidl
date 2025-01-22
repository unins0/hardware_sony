package vendor.semc.hardware.extlight;

@VintfStability
interface IExtLight {
    vendor.semc.hardware.extlight.HwExtLight[] getLights();
    boolean isBoosted();
    boolean isSunlightBoosted();
    void registerCallback(in vendor.semc.hardware.extlight.IBoostedCallback iBoostedCallback);
    int setExtHdr(in int i);
    void setExtLightState(in int i, in android.hardware.light.HwLightState hwLightState);
    int setExtSunlight(in int i);
}
