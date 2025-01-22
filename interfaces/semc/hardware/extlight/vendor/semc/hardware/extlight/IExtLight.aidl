package vendor.semc.hardware.extlight;
import android.hardware.light.HwLightState;
import vendor.semc.hardware.extlight.IBoostedCallback;
import vendor.semc.hardware.extlight.HwExtLight;

@VintfStability
interface IExtLight {
    HwExtLight[] getLights();

    boolean isBoosted();

    boolean isSunlightBoosted();

    void registerCallback(in IBoostedCallback iBoostedCallback);

    int setExtHdr(in int i);

    void setExtLightState(in int i, in HwLightState hwLightState);

    int setExtSunlight(in int i);
}
