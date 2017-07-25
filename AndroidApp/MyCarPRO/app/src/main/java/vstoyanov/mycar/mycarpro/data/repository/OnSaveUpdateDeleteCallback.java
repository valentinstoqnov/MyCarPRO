package vstoyanov.mycar.mycarpro.data.repository;

public interface OnSaveUpdateDeleteCallback {

    void onSuccess(String name);

    void onFailure();
}
