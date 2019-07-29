import React from 'react';
import ReactDOM from 'react-dom';

class HelloWorld extends React.Component {
  render() {
    return (
      <span>Hello World from React built with Node! (v2.0)</span>
    );
  }
}

ReactDOM.render(
  <HelloWorld />,
  document.getElementById('root')
);
