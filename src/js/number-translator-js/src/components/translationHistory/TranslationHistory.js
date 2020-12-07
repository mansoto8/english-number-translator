import React, {Component} from 'react';
import {Container,Button} from 'reactstrap';
import './TranslationHistory.css';

class TranslationHistory extends Component {

    translations = [];

    constructor(props) {
        super(props);
        this.state = {
            translations: this.translations,
        };
        this.getHistory = this.getHistory.bind(this);
    }

    async getHistory(event) {
        event.preventDefault();

        const response = await fetch('/api/numbers', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });

        const translations = await response.json();
        this.setState({translations});
    }

    render() {
        const {translations} = this.state;

        return <div>
            <Container>
                <Button id="history-button" color="primary" type="button"
                        onClick={this.getHistory}>Check Translation History</Button>
                <table id="history-table">
                    <thead>
                    <tr>
                        <th>Number</th>
                        <th>Translation</th>
                    </tr>
                    </thead>
                    <tbody>
                    {translations.map(translation =>
                        <tr key={translation.number}>
                            <td>{translation.number.toLocaleString()}</td>
                            <td>{translation.translation}</td>
                        </tr>
                    )}
                    </tbody>
                </table>
                {translations.length === 0 ? (<p>No registers</p>) : (<p></p>)}
            </Container>
        </div>
    }
}

export default TranslationHistory;
