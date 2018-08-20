package com.chatty.android.chattyClient.externalModules.Renderer;

import java.util.HashMap;
import java.util.List;

public class Renderer {
  static HashMap<Object, List<Object>> instanceDataMap = new HashMap<>();

  public static boolean render(
    Object instance,
    List<Object> data,
    Updater ...updaters
  ) {
    boolean instanceIsFound = true;

    if (data.size() != updaters.length) {
      // "render() needs the same length of data and updaters"
      return false;
    }

    List<Object> previousData = Renderer.instanceDataMap.get(instance);

    if (previousData == null) {
      Renderer.instanceDataMap.put(instance, data);
      instanceIsFound = false;
    }

    for (int i = 0; i < data.size(); i++) {
      if (!instanceIsFound || data.get(i) != previousData.get(i)) {
        updaters[i].run(data.get(i));
      }
    }

    return true;
  }

  public interface Updater {
    void run(Object o);
  }
}
