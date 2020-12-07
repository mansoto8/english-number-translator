import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import TranslatorForm from "./components/TranslatorForm";
import TranslationHistory from "./components/TranslationHistory";

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
  }

  render() {
    const {translations, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div className="App">
          <header className="App-header">
              English Number Translator
          </header>
          <TranslatorForm/>
          <TranslationHistory/>
        </div>
    );
  }
}

export default App;
