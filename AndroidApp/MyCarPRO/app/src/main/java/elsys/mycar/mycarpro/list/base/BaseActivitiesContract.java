package elsys.mycar.mycarpro.list.base;

import java.util.List;

import elsys.mycar.mycarpro.base.BasePresenter;
import elsys.mycar.mycarpro.base.BaseView;

public interface BaseActivitiesContract {

    interface View<T> extends BaseView<Presenter<T>> {

        void showItems(List<T> items);

        void showNoItemsFound();

        void showItemsFetchError();

        void showDetailsItemUi(String itemId);

        void showProgress();

        void hideProgress();

        boolean isActive();
    }

    interface Presenter<T> extends BasePresenter {

        void openItemDetails(T item);

        boolean isDataMissing();
    }
}
