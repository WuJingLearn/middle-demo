package org.javaboy.mediator.home;

import java.util.*;

/**
 * @author majin.wj
 * @date 2023/6/28 9:46 PM
 * @desc
 */
public class HomeMediator {

    List<Home> homeInfo = new ArrayList<>();

    Map<GetHomeRquest, List<HomeInfoListener>> listenerMap = new HashMap<>();

    /**
     * 发布房产信息
     *
     * @param home
     */
    public synchronized void publishHome(Home home) {
        homeInfo.add(home);
        notify(home);
    }

    void notify(Home home) {
        GetHomeRquest getHomeRquest = home.coverRequest();
        List<HomeInfoListener> homeInfoListeners = listenerMap.get(getHomeRquest);
        if (homeInfoListeners != null && homeInfoListeners.size() > 0) {
            homeInfoListeners.forEach(listener -> listener.notify(home));
        }
    }


    public synchronized Home getHome(GetHomeRquest request, HomeInfoListener homeListener) {
        Home result = null;
        for (Home home : homeInfo) {
            if (home.isAdapter(request)) {
                result = home;
                break;
            }
        }
        if (result != null) {
            homeInfo.remove(result);
            Optional.ofNullable(listenerMap.get(request))
                    .ifPresent(listeners->listeners.remove(homeListener));

            return result;
        }
        listenerMap.putIfAbsent(request, new ArrayList<>());
        listenerMap.get(request).add(homeListener);
        return null;
    }


}
