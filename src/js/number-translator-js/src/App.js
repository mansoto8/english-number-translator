import React, {Component} from 'react';
import './App.css';
import TranslatorForm from "./components/translatorForm/TranslatorForm";
import TranslationHistory from "./components/translationHistory/TranslationHistory";

/**
 * Main components that contains the form that allows to request number translations and a component
 * that shows the history of requests.
 */
class App extends Component {
    async componentDidMount() {
        this.setState(this.state);
    }

    render() {
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
