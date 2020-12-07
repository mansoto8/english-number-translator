import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: false,
    translations: []
  };

  async componentDidMount() {
    const response = await fetch('/api/numbers');
    const body = await response.json();
    this.setState({ translations: body, isLoading: false });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/numbers/' + (item.id ? '/' + item.id : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/groups');
  }

  render() {
    const {translations, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Translation list</h2>
                  <div>
                    <table>
                      <tr>
                        <th>Number</th>
                        <th>translation</th>
                      </tr>
                      {translations.length==0 ? (<p>No registers</p>) : (<p></p>)}
                      {translations.map(translation =>
                      <tr>
                        <td>{translation.number}</td>
                        <td>{translation.translation}</td>
                      </tr>
                      )}
                    </table>
                  </div>
            </div>
          </header>
        </div>
    );
  }
}

export default App;
