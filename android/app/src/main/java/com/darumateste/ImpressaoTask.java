package com.darumateste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import br.com.daruma.framework.mobile.DarumaMobile;

// ---------------------------------------------------------------------------------------------
// AsyncTask para execução das impressões
// ---------------------------------------------------------------------------------------------
@SuppressLint("StaticFieldLeak")
public class ImpressaoTask {
  private DarumaMobile mDarumaMobile;
  private String mModeloImpressora = "DRM380";
  private Context context;


  public void setPrinter(Context context, String printer) {
    this.mModeloImpressora = printer;
    this.context = context;
  }

  public void printText(String text) {
    this.mDarumaMobile = DarumaMobile.inicializar(this.context,
      "@BLUETOOTH(NAME=" + this.mModeloImpressora + ";TIMEOUT=2000;)");
    this.mDarumaMobile.confParametros("@FRAMEWORK(TRATAEXCECAO=TRUE;LOGMEMORIA=500;)");
    this.mDarumaMobile.iniciarComunicacao();
    this.mDarumaMobile.enviarComando("" + ((char) 0x1C) + ((char) 0x2E)
      + "" + ((char) 0x1B) + ((char) 0x74) + ((char) 0x10));
    this.mDarumaMobile.enviarComando(text + "\n\n\n");
    this.mDarumaMobile.fecharComunicacao();
  }

  public void imprimirBufferCompletoDR800() {

    this.mDarumaMobile = DarumaMobile.inicializar(this.context,
      "@BLUETOOTH(NAME=" + this.mModeloImpressora + ";TIMEOUT=2000;)");
    this.mDarumaMobile.confParametros("@FRAMEWORK(TRATAEXCECAO=TRUE;LOGMEMORIA=500;)");
    this.mDarumaMobile.iniciarComunicacao();

    this.mDarumaMobile.enviarComando(((char) 0x1B) + "@" + "" + ((char) 0x1B) + "j1" + ""
      + ((char) 0x0A) + "FORMATAÇÃO DE TEXTO" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "Formatação Normal: "
      + "" + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGR+ITAL+SUBL+EXPAND: " + ""
      + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x1B) + ((char) 0x34)
      + ((char) 0x01) + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "NEGR+ITAL+SUBL+CONDENSADO: " + "" + ((char) 0x1B) + "" + ((char) 0x45) + ""
      + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x1B) + "-"
      + ((char) 0x01) + "" + ((char) 0x0F) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "NEGR+ITAL+SUBL+NORMAL: " + "" + ((char) 0x1B) + "" + ((char) 0x45)
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x1B)
      + "-" + ((char) 0x01) + "" + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "EXPANDIDO: "
      + "" + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A)
      + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "CONDENSADO: " + ""
      + ((char) 0x0F) + "" + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A)
      + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+EXPANDIDO: "
      + "" + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0E) + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+EXPANDIDO: "
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "SUBLINHADO+EXPANDIDO: " + "" + ((char) 0x1B) + "-" + ((char) 0x01) + ""
      + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+CONDENSADO: "
      + "" + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0F) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+CONDENSADO: "
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0F) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "SUBLINHADO+CONDENSADO: "
      + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0F) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+NORMAL: " + ""
      + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+NORMAL: " + ""
      + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "SUBLINHADO+NORMAL: "
      + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ALINHADO A DIREITA: "
      + "" + ((char) 0x1B) + "j2" + "" + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "ALINHADO A DIREITA + EXPANDIDO: " + "" + ((char) 0x1B) + "j2" + ""
      + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ALINHADO A DIREITA + SUBLINHADO: "
      + "" + ((char) 0x1B) + "j2" + "" + ((char) 0x1B) + "-" + ((char) 0x01) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "CENTRALIZADO + EXPANDIDO: " + "" + ((char) 0x1B) + "j1" + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "Dupla Altura: " + ""
      + ((char) 0x1B) + "w" + ((char) 0x01) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + "" + ((char) 0x19) + "" + ((char) 0x19) + "" + ((char) 0x1B)
      + "" + ((char) 0x6D) + "" + ((char) 0x19) + ((char) 0x1B)
      + "" + ((char) 0x81) + "" + ((char) 0x29) + "" + ((char) 0x00) + ""
      + ((char) 0x07) + ((char) 0x00) + "http://www.desenvolvedoresdaruma.com.br"
      + "Leia o QRCode para acessar nosso portal!" + ((char) 0x1B)
      + "j0" + ((char) 0x1B) + "" + ((char) 0x6D) + "\n\n"
      + ((char) 0x1B) + "@" + "" + ((char) 0x1B) + "j0" + ""
      + ((char) 0x0A) + "EAN-13: Vertical, Largura 3, Altura 70" + ""
      + ((char) 0x0A) + "" + ((char) 0x0A) + "" + ((char) 0x1B) + "@"
      + ((char) 0x1B) + "a" + ((char) 0x01) + ((char) 0x03) + "F"
      + ((char) 0x00) + "123456789123" + ((char) 0x00) + ((char) 0xFF)
      + ((char) 0x0A) + "" + "" + ((char) 0x0A) + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "" + ((char) 0x6D));
  }

  private void imprimirBufferCompletoDRM380() {
    this.mDarumaMobile.enviarComando(((char) 0x1B) + "@" + ((char) 0x1D) + ((char) 0x68) +
      ((char) 0x50) + "------------------------------------------------\n" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + "!" + ((char) 0x01) + "PAULISTAO 2017/18 " +
      "CLASSIFICACAO\n" + "CAMPEONATO PAULISTA DE FUTEBOL\n" + ((char) 0x1B) + "!" +
      ((char) 0x00) + ((char) 0x1B) + "a0" +
      "------------------------------------------------\n" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + "!" + ((char) 0x10) + "PONTE PRETA x " +
      "GUARANI\n" + ((char) 0x1B) + "!" + ((char) 0x00) + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "!" + ((char) 0x80) + "ESTADIO BRINCO DE OURO DA PRINCESA\n" +
      ((char) 0x1B) + "!" + ((char) 0x00) + ((char) 0x1B) + "!" + ((char) 0x20) +
      "05/03/2017 - 10:00:00\n" + ((char) 0x1B) + "!" + ((char) 0x00) +
      ((char) 0x1B) + "!" + ((char) 0x08) + "PORTAO 1 | ARQUIBANCADA\n" +
      ((char) 0x1B) + "!" + ((char) 0x00) +
      ((char) 0x1B) + "a2" + ((char) 0x1B) + "{1" + "Numero: 7703320 - R$ 0,00 - " +
      "CORTESIA\n" + ((char) 0x1B) + "{0" + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "a1" + ((char) 0x1D) + ((char) 0x6B) + ((char) 0x02) +
      "001239496030" + ((char) 0x00) + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "a1" + ((char) 0x1D) + "B1" + "\n\nIngresso valido somente com " +
      "canhoto\n" + ((char) 0x1D) + "B0" + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + ((char) 0x20) + "5" + "BOM JOGO\n\n" +
      ((char) 0x1B) + ((char) 0x20) + "0" + ((char) 0x1B) + "a0" + ((char) 0x00) +
      ((char) 0x1B) + "@" + ((char) 0x1B) + "a1" +
      ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00) +
      ((char) 0x31) + ((char) 0x43) + ((char) 0x05) +
      ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00) +
      ((char) 0x31) + ((char) 0x45) + ((char) 0x30) +
      ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) 0x0B) + ((char) 0x00) +
      ((char) 0x31) + ((char) 0x50) + ((char) 0x30) +
      ((char) 0x4C) + ((char) 0x45) + ((char) 0x4F) + ((char) 0x4E) + ((char) 0x41) +
      ((char) 0x52) + ((char) 0x44) + ((char) 0x4F) +
      ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00) +
      ((char) 0x31) + ((char) 0x51) + ((char) 0x30) +
      ((char) 0x1B) + "a0" + "\n\n\n\n" + ((char) 0x00));
  }

  private void imprimirTextoFormatadoDR800() {
    this.mDarumaMobile.enviarComando(((char) 0x1B) + "@" + "" + ((char) 0x1B) + "j1" + ""
      + ((char) 0x0A) + "FORMATAÇÃO DE TEXTO" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "Formatação Normal: "
      + "" + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGR+ITAL+SUBL+EXPAND: " + ""
      + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x1B) + ((char) 0x34)
      + ((char) 0x01) + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "NEGR+ITAL+SUBL+CONDENSADO: " + "" + ((char) 0x1B) + "" + ((char) 0x45) + ""
      + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x1B) + "-"
      + ((char) 0x01) + "" + ((char) 0x0F) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "NEGR+ITAL+SUBL+NORMAL: " + "" + ((char) 0x1B) + "" + ((char) 0x45)
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x1B)
      + "-" + ((char) 0x01) + "" + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "EXPANDIDO: "
      + "" + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A)
      + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "CONDENSADO: " + ""
      + ((char) 0x0F) + "" + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A)
      + "" + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+EXPANDIDO: "
      + "" + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0E) + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+EXPANDIDO: "
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "SUBLINHADO+EXPANDIDO: " + "" + ((char) 0x1B) + "-" + ((char) 0x01) + ""
      + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+CONDENSADO: "
      + "" + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0F) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+CONDENSADO: "
      + "" + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0F) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "SUBLINHADO+CONDENSADO: "
      + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0F) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "NEGRITO+NORMAL: " + ""
      + ((char) 0x1B) + "" + ((char) 0x45) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ITÁLICO+NORMAL: " + ""
      + ((char) 0x1B) + ((char) 0x34) + ((char) 0x01) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "SUBLINHADO+NORMAL: "
      + "" + ((char) 0x1B) + "-" + ((char) 0x01) + "" + ((char) 0x0A)
      + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ALINHADO A DIREITA: "
      + "" + ((char) 0x1B) + "j2" + "" + ((char) 0x0A) + "Daruma Tecnologia!" + ""
      + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "ALINHADO A DIREITA + EXPANDIDO: " + "" + ((char) 0x1B) + "j2" + ""
      + ((char) 0x0E) + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "ALINHADO A DIREITA + SUBLINHADO: "
      + "" + ((char) 0x1B) + "j2" + "" + ((char) 0x1B) + "-" + ((char) 0x01) + ""
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A)
      + "CENTRALIZADO + EXPANDIDO: " + "" + ((char) 0x1B) + "j1" + "" + ((char) 0x0E)
      + ((char) 0x0A) + "Daruma Tecnologia!" + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "@" + "" + ((char) 0x0A) + "Dupla Altura: " + ""
      + ((char) 0x1B) + "w" + ((char) 0x01) + "" + ((char) 0x0A) + "Daruma Tecnologia!"
      + "" + ((char) 0x0A) + "" + "" + ((char) 0x0A) + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "" + ((char) 0x6D));
  }

  private void imprimirTextoFormatadoDRM380() {
    this.mDarumaMobile.enviarComando(((char) 0x1B) + "@" + ((char) 0x1D) + ((char) 0x68) +
      ((char) 0x50) + "------------------------------------------------\n" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + "!" + ((char) 0x01) + "PAULISTAO 2017/18 " +
      "CLASSIFICACAO\n" + "CAMPEONATO PAULISTA DE FUTEBOL\n" + ((char) 0x1B) + "!" +
      ((char) 0x00) + ((char) 0x1B) + "a0" + "------------------------------------------------\n" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + "!" + ((char) 0x10) + "PONTE PRETA x GUARANI\n" +
      ((char) 0x1B) + "!" + ((char) 0x00) + ((char) 0x1B) + "a0" + ((char) 0x1B) + "!" +
      ((char) 0x80) + "ESTADIO BRINCO DE OURO DA PRINCESA\n" + ((char) 0x1B) + "!" +
      ((char) 0x00) + ((char) 0x1B) + "!" + ((char) 0x20) + "05/03/2017 - 10:00:00\n" +
      ((char) 0x1B) + "!" + ((char) 0x00) +
      ((char) 0x1B) + "!" + ((char) 0x08) + "PORTAO 1 | ARQUIBANCADA\n" + ((char) 0x1B) +
      "!" + ((char) 0x00) + ((char) 0x1B) + "a2" + ((char) 0x1B) + "{1" + "Numero: " +
      "7703320 - R$ 0,00 - CORTESIA\n" + ((char) 0x1B) + "{0" + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "a1" + ((char) 0x1D) + "B1" + "\n\nIngresso valido somente com " +
      "canhoto\n" + ((char) 0x1D) + "B0" + ((char) 0x1B) + "a0" +
      ((char) 0x1B) + "a1" + ((char) 0x1B) + ((char) 0x20) + "5" + "BOM JOGO\n\n" +
      ((char) 0x1B) + ((char) 0x20) + "0" + ((char) 0x1B) + "a0" + ((char) 0x00) +
      "\n\n\n\n" + ((char) 0x00));
  }

  private void imprimirQrCodeDRM380() {
    // Para visualizar um exemplo de montagem dinâmica do conteúdo do QrCode da DRM380,
    // acesse: http://www.desenvolvedoresdaruma.com.br/home/downloads/Site_2011/DMF/Exemplos/Exemplo_QrCode_DRM380.zip

    String textoQrCode = "Daruma Developers Community - www.desenvolvedoresdaruma.com.br";
    int iTamanho = textoQrCode.length();
    int bMais = (iTamanho & 0xff) + 3;    //AND COM 255
    int bMenos = iTamanho >> 8;

    String configModulo = ((char) 0x1B) + "@" + ((char) 0x1D) + ((char) 0x28)
      + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00) + ((char) 0x31)
      + ((char) 0x43) + ((char) 0x08);

    String configCorrecao = "" + ((char) 0x1D) + ((char) 0x28)
      + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00) + ((char) 0x31)
      + ((char) 0x45) + ((char) 0x50);

    String qrcode = "" + ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) bMais)
      + ((char) bMenos) + ((char) 0x31) + ((char) 0x50) + ((char) 0x30)
      + textoQrCode
      + ((char) 0x1D) + ((char) 0x28) + ((char) 0x6B) + ((char) 0x03) + ((char) 0x00)
      + ((char) 0x31) + ((char) 0x51) + ((char) 0x30) + ((char) 0x00) + "\n\n\n";

    this.mDarumaMobile.enviarComando(configModulo);
    this.mDarumaMobile.enviarComando(configCorrecao);
    this.mDarumaMobile.enviarComando(qrcode);
  }

  private void imprimirQrCodeDR800() {
    this.mDarumaMobile.enviarComando("" + ((char) 0x1B) + "" + ((char) 0x81) + ""
      + ((char) 0x29) + "" + ((char) 0x00) + "" + ((char) 0x07)
      + ((char) 0x00)
      + "http://www.desenvolvedoresdaruma.com.br"
      + "Leia o QRCode para acessar nosso portal!" + ((char) 0x1B)
      + "j0" + ((char) 0x0A) + "" + ((char) 0x19) + ""
      + ((char) 0x1B) + "" + ((char) 0x6D));
  }

  private void imprimirCodBarrasDRM380() {
    String codbarras = ((char) 0x1B) + "@" + ((char) 0x1D) + ((char) 0x68) +
      ((char) 0xA2) + ((char) 0x1B) + "a1" + ((char) 0x1D) + ((char) 0x6B) +
      ((char) 0x02) + "0123456789876" + ((char) 0x00) + ((char) 0x1B) + "a0" +
      "\n\n\n" + ((char) 0x00);

    this.mDarumaMobile.enviarComando(codbarras);
  }

  private void imprimirCodBarrasDR800() {
    this.mDarumaMobile.enviarComando(((char) 0x1B) + "@" + "" + ((char) 0x1B) + "j0" + ""
      + ((char) 0x0A) + "EAN-13: Vertical, Largura 3, Altura 70" + ""
      + ((char) 0x0A) + "" + ((char) 0x0A) + "" + ((char) 0x1B) + "@"
      + "" + ((char) 0x1B) + "a" + ((char) 0x01) + ((char) 0x03) + "F"
      + ((char) 0x00) + "123456789123" + ((char) 0x00) + ((char) 0xFF)
      + ((char) 0x0A) + "" + ((char) 0x0A) + "" + ((char) 0x0A) + ""
      + ((char) 0x1B) + "" + ((char) 0x6D));
  }
}
