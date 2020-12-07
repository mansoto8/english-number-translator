import React, {Component} from 'react';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import './TranslatorForm.css';

class TranslatorForm extends Component {

    emptyItem = {
        number: 0,
        translation: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        let {item} = this.state;

        const translation = await fetch('/api/numbers' + (item.number ? '/' + item.number : ''), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });

        const resp = await translation.json();
        item.translation = resp.output;
        this.setState({item});
    }

    render() {
        const {item} = this.state;

        return <div>
            <Container>
                <Form onSubmit={this.handleSubmit} className={"translator-form"}>
                    <Input type="number" name="number" id="number-input" value={item.number || ''}
                           onChange={this.handleChange} placeholder="Enter a number"/>
                    <Button color="primary" type="submit">Translate</Button>{' '}
                </Form>
                <div className="result-div">
                    <Label for="translation-output" id={"translation-output-label"}>The number translated is: </Label>
                    <Input type="text" name="translation-output" id="translation-output" value={item.translation || ''}
                           readOnly/>
                </div>
            </Container>
        </div>
    }
}

export default TranslatorForm;
