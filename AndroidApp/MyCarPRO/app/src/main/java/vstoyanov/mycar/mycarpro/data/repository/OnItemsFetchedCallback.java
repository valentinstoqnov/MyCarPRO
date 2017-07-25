package vstoyanov.mycar.mycarpro.data.repository;

import java.util.List;

public interface OnItemsFetchedCallback<T> {

    void onSuccess(List<T> items);

    void onFailure();
}
