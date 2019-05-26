package e.mi.myapplication.Interfaces;

import java.util.List;

import e.mi.myapplication.Adapters.CitiesAdapter;
import e.mi.myapplication.Net.*;

public interface MainInterface {

    //Отвечает за получение данных. Реализуется в специальном классе для делов с бэкендом
//    interface presenter {
//        void getDataFromServer(int itemId);
//    }

    //Отвечает за визуальное наполнение. Реализуется в MainActivity

    //Отвечает, какие данные и как будут загружаться в фоне.
    interface intractor {

        //Решает, как должна вести себя программа, когда данные загружены с сервера
        interface onLoadDataListener {
            void onLoadEventFinished(Events events);
            void onLoadCitiesFinished(List<City> cities);
            void onLoadCategoriesFinished(List<Category> categories);
        }

        //Инициализиацирует ретрофит, получает данные и передает их
        //onLoadDataLister, который знает, как с ними обращаться
        void loadData(onLoadDataListener listener, int itemId);
    }
}
