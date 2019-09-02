import styled from 'styled-components/native';

export const Container = styled.View`
  flex: 1;
`;

export const Body = styled.View`
  justify-content: center;
  align-items: center;
  padding: 5px;
`;

export const Header = styled.View`
  background-color: #7159c1;
  height: 60px;
  justify-content: center;
  align-items: center;
  padding: 15px;
`;

export const Title = styled.Text`
  color: #fff;
  font-size: 16px;
  font-weight: bold;
`;

export const DispList = styled.FlatList``;

export const Input = styled.TextInput`
  padding: 0 15px;
  height: 46px;
  width: 90%;
  background: rgba(0, 0, 0, 0.1);
  border-radius: 4px;

  flex-direction: row;
  align-items: center;
`;
