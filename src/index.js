import React, { useState, useEffect } from 'react';
import { Text } from 'react-native';

import './config/ReactotronConfig';
import Daruma from '../Daruma';

import { Container, Header, Title, DispList, Body, Input } from './styles';
import Button from './components/Button';

export default function Index() {
  const [disps, setDisps] = useState([]);
  const [printer, setPrinter] = useState('');
  const [text, setText] = useState('');

  useEffect(() => {
    async function ListDisp() {
      Daruma.fnListaDispositivosBluetooth(
        msg => {
          console.tron.log(msg);
        },
        dispsMatched => {
          setDisps(
            dispsMatched
              .replace('[', '')
              .replace(']', '')
              .replace(' ', '')
              .split(',')
          );
        }
      );
    }

    ListDisp();
  }, []);

  function handleSetPrinter(item) {
    Daruma.show(item, Daruma.SHORT);
    setPrinter(item);
  }

  async function handlePrint() {
    console.tron.log(text);
    Daruma.printText(text, printer);
  }

  return (
    <Container>
      <Header>
        <Title>Daruma Printer</Title>
        <Text>{printer}</Text>
      </Header>
      <Body>
        <DispList
          data={disps}
          keyExtractor={item => String(item)}
          renderItem={({ item }) => (
            <Text onPress={() => handleSetPrinter(item)}>{item}</Text>
          )}
        />
        <Input onChangeText={e => setText(e)} />
        <Button onPress={async () => Daruma.testPrint(printer)}>
          Testar impressão
        </Button>
        <Button onPress={() => handlePrint()}>Imprimir</Button>
      </Body>
    </Container>
  );
}
