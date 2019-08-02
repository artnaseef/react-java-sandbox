import React from 'react';
import ReactDOM from 'react-dom';

class HelloWorld extends React.Component {
    constructor(props) {
        super(props);
        this.state = {serverText: ""};
    }

    receivedServerText(text) {
        console.log(text)
        this.setState({ serverText: text });
    }

    render() {
        fetch('/api/hello')
            .then(function (response) {
                      return response.text();
                  })
            .then((responseText) => {
                      this.receivedServerText(responseText);
                  }
        );

        return (
            <div>
                <span>Hello World from React built with Node! (v2.0)</span>
                <br/>
                <span><b>server-text:</b> {this.state.serverText}</span>
            </div>
        );
    }
}

ReactDOM.render(
    <HelloWorld />,
    document.getElementById('root')
);
