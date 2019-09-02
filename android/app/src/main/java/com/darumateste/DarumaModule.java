package com.darumateste;

import android.widget.Toast;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import br.com.daruma.framework.mobile.DarumaMobile;

public class DarumaModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";
  private static final int PERMISSOES = 10;
  private static final String LOG_TAG = "Daruma_Log";


  public DarumaModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "Daruma";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }

  @ReactMethod
  public void show(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  @ReactMethod
  public void testPrint(String printer) {
    try {
      ImpressaoTask task = new ImpressaoTask();
      task.setPrinter(getReactApplicationContext(), printer);
      task.imprimirBufferCompletoDR800();
    } catch (Exception e) {
      this.show(e.getMessage(), 3);
    }
  }

  @ReactMethod
  public void printText(String text, String printer) {
    try {
      ImpressaoTask task = new ImpressaoTask();
      task.setPrinter(getReactApplicationContext(), printer);
      task.printText(text);
    } catch (Exception e) {
      this.show(e.getMessage(), 3);
    }
  }

  /**
   * @return Nomes e MAC ADRESS dos dispositivos Bluetooth pareados com o Android
   */
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  @ReactMethod
  public Object[] fnListaDispositivosBluetooth(Callback errorCallback,
                                               Callback successCallback) {
    ArrayList<String> nomesArrayList = new ArrayList<>();


    try {
      checaPermissoes();
    } catch (Exception e) {
      Log.e(LOG_TAG, e.getMessage());
      checaPermissoes();
    }
    DarumaMobileUtil darumaMobileUtil = new DarumaMobileUtil();

    try {
      List<String> dispositivosList = darumaMobileUtil.fnListaDispositivosBluetooth();

      for (String dispositivo : dispositivosList) {
        String[] atual = dispositivo.split("\\[");
        nomesArrayList.add(atual[1].substring(0, atual[1].length() - 1));
      }

      successCallback.invoke(nomesArrayList.toString());
    } catch (Exception e) {
      errorCallback.invoke(e.getMessage());
    }
    return nomesArrayList.toArray();
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  private void checaPermissoes() {
    ActivityCompat.requestPermissions(Objects.requireNonNull(getCurrentActivity()), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.BLUETOOTH,
      Manifest.permission.BLUETOOTH_ADMIN,
      Manifest.permission.INTERNET}, PERMISSOES);
  }
}
