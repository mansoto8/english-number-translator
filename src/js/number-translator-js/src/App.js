import React, { Component } from 'react';
import './App.css';
import TranslatorForm from "./components/translatorForm/TranslatorForm";
import TranslationHistory from "./components/translationHistory/TranslationHistory";

class App extends Component {
  state = {
    translations: []
  };

  async componentDidMount() {
    this.setState(this.state);
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
