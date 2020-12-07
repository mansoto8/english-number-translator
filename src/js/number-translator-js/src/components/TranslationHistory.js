import React, {Component} from 'react';
import {Button, Form} from 'reactstrap';

class TranslationHistory extends Component {

    translations = [];

    constructor(props) {
        super(props);
        this.state = {
            translations: this.translations,
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async handleSubmit(event) {
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
            <h2>Translation list</h2>
            <div>
                <Form onSubmit={this.handleSubmit}>
                    <Button color="primary" type="submit">Check Translation History</Button>{' '}
                </Form>
                {translations.length == 0 ? (<p>No registers</p>) : (<p></p>)}
                <table>
                    <thead>
                        <tr>
                            <th>Number</th>
                            <th>Translation</th>
                        </tr>
                    </thead>
                    <tbody>
                        {translations.map(translation =>
                            <tr key={translation.number}>
                                <td>{translation.number}</td>
                                <td>{translation.translation}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    }
}

export default TranslationHistory;
