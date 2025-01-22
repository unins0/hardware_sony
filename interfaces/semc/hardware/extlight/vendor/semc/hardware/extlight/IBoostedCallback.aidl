package vendor.semc.hardware.extlight;

@VintfStability
interface IBoostedCallback {
    void onBoostedEvent(in boolean boost);
}
