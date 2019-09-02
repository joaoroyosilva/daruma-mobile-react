package com.darumateste;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.util.List;

import br.com.daruma.framework.mobile.DarumaMobile;
import com.darumateste.R;

/**
 * Classe utilizada para instanciamento e trabalho com a DarumaMobileFrameWork
 */

public class DarumaMobileUtil {
  private static final String LOG_TAG = "Daruma_Log";
  private static DarumaMobile mDarumaMobileFrameWork;
  private SharedPreferences mPreferencias;

  /**
   * Retorna a instância da DarumaMobile FrameWork para evitar múltiplas instâncias
   *
   * @return instância da DarumaModile FrameWork já criada.
   */
  public static DarumaMobile getInstanciaDmf() {
    if (DarumaMobileUtil.mDarumaMobileFrameWork != null) {
      return DarumaMobileUtil.mDarumaMobileFrameWork;
    } else {
      throw new RuntimeException("DarumaMobile FrameWork não instânciada.");
    }
  }

  /**
   * Inicialização para conexões do tipo Bluetooth ou Serial com qualquer modelo de impressora
   *
   * @param context        contexto necessário para inicialização da instância da DMF
   * @param nomePareamento nome da impressora pareada com o Dispositivo Android
   */
  public void fnInicializarDmf(Context context, String nomePareamento) {
    mPreferencias = context.getSharedPreferences(
      context.getString(R.string.shared_preferences_nome), Context.MODE_PRIVATE);
    String tipoComunicacao = mPreferencias.getString("tipoComunicacao", "");

    try {
      switch (tipoComunicacao) {
        case "Bluetooth": {
          DarumaMobileUtil.mDarumaMobileFrameWork = DarumaMobile.inicializar(context,
            "@BLUETOOTH(NAME=" + nomePareamento + ";TIMEOUT=2000;)");
          DarumaMobileUtil.mDarumaMobileFrameWork.confParametros("@FRAMEWORK(TRATAEXCECAO=TRUE;LOGMEMORIA=500;)");

          Log.i(LOG_TAG, "DarumaMobile Instanciada para conexão Bluetooth" +
            "\nNome Pareamento: " + nomePareamento +
            "\nModelo Impressora: " + mPreferencias.getString("modeloImpressora", ""));
        }
        break;

        case "Serial": {
          //Aplicação iniciando sempre com a porta serial na posição 0, deve ser alterado
          // caso dispositivo tenha mais barramentos internos
          DarumaMobileUtil.mDarumaMobileFrameWork = DarumaMobile.inicializar(context,
            "@SERIAL(PORTA=0;VELOCIDADE=115200;)");
          DarumaMobileUtil.mDarumaMobileFrameWork.confParametros("@FRAMEWORK(TRATAEXCECAO=TRUE;LOGMEMORIA=500;)");
          Log.i(LOG_TAG, "DarumaMobile Instanciada para conexão Serial:" +
            "\nNúmero Porta: " + nomePareamento +
            "\nModelo Impressora: " + mPreferencias.getString("modeloImpressora", ""));
        }
        break;
      }

      this.configurarXmlSatDaruma();
    } catch (Exception e) {
      Log.e(LOG_TAG, "Erro no instânciamento da DarumaMobile: " + e.getMessage());
    }
  }

  /**
   * Inicialização para conexões através do Wi-fi para qualquer modelo de impressora
   *
   * @param context contexto necessário para inicialização da instância da DMF
   * @param ip      IP da impressora para conexão
   * @param porta   Número da porta para conexão com o dispositivo
   */
  public void fnInicializarDmf(Context context, String ip, String porta) {
    mPreferencias = context.getSharedPreferences(
      context.getString(R.string.shared_preferences_nome), Context.MODE_PRIVATE);

    try {
      DarumaMobileUtil.mDarumaMobileFrameWork = DarumaMobile.inicializar(context,
        "@SOCKET(HOST=" + ip + ";PORT=" + porta + ";)");
      DarumaMobileUtil.mDarumaMobileFrameWork.confParametros("@FRAMEWORK(TRATAEXCECAO=TRUE;LOGMEMORIA=500;)");

      Log.i(LOG_TAG, "DarumaMobile Instanciada para conexão Wi-fi" +
        "\nIP: " + ip +
        "\nPorta: " + porta +
        "\nModelo Impressora: " + mPreferencias.getString("modeloImpressora", ""));

      this.configurarXmlSatDaruma();
    } catch (Exception e) {
      Log.e(LOG_TAG, "Erro no instânciamento da DarumaMobile: " + e.getMessage());
      throw e;
    }
  }

  /**
   * @return Nomes e MAC ADRESS dos dispositivos Bluetooth pareados com o Android
   */
  public List<String> fnListaDispositivosBluetooth() {
    return DarumaMobile.retornaDispositivosBluetooth();
  }

  /**
   * Função interna para configuração do GNE_FrameWork.xml e SAT_FrameWork.xml com os valores
   * de teste do SAT Daruma
   */
  private void configurarXmlSatDaruma() {
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_NFCe("CONFIGURACAO\\HabilitarSAT", "1");

    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("CONFIGURACAO\\LocalArquivos",
      Environment.getExternalStorageDirectory().getPath() + "/");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("CONFIGURACAO\\CopiaSeguranca", "0");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("CONFIGURACAO\\Concentrador", "0");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("CONFIGURACAO\\marcaSAT", "DARUMA");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("CONFIGURACAO\\codigoDeAtivacao", "DS100iSDK");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("IDENTIFICACAO_CFE\\CNPJ", "16716114000172");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("IDENTIFICACAO_CFE\\numeroCaixa", "001");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("IDENTIFICACAO_CFE\\signAC", "SGR-SAT SISTEMA DE GESTAO E RETAGUARDA DO SAT");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("EMIT\\CNPJ", "45170289000125");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("EMIT\\IE", "688023460111");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("EMIT\\indRatISSQN", "N");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("EMIT\\cRegTribISSQN", "1");
    DarumaMobileUtil.mDarumaMobileFrameWork.RegAlterarValor_SAT("PROD\\indRegra", "A");
  }
}
