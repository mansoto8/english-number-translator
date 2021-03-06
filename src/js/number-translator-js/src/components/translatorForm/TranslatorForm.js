import {Container, FormGroup, Label, FormFeedback, Form, Input, InputGroup, Button} from 'reactstrap';
import React, {Component} from 'react';
import './TranslatorForm.css';

/**
 * Component that contains a form that allows the user to send numbers and also a container
 * that shows the result of the translation returned by the backend.
 */
class TranslatorForm extends Component {

    emptyItem = {
        number: undefined,
        translation: '',
    };

    validate = {
        number: 'valid-number-format',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            validate: this.validate,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.isValidNumberFormat = this.isValidNumberFormat.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    /**
     * Sends the number to the REST endpoint that returns the translation. Previous
     * to sending the request validations of format and size are performed, if the fail
     * the state is updated with the error and no request is sent.
     *
     * @param event
     * @returns {Promise<void>} With the text representation of the number
     */
    async handleSubmit(event) {
        event.preventDefault();
        let {item, validate} = this.state;

        let number = item.number;
        if (!this.isValidNumberFormat(number)) {
            validate.number = 'invalid-number-format';
            this.setState({validate});
            return;
        }

        if (number.indexOf(',') > 0) {
            number = number.replace(/,/g, '');
        }

        if (Math.abs(number) > 9999999999) {
            validate.number = 'invalid-number-size';
            this.setState({validate});
            return;
        }

        const translation = await fetch('/api/numbers/' + number, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });

        const resp = await translation.json();
        item.translation = resp.output;
        validate.number = 'valid-number-format';
        this.setState({item, validate});
    }

    /**
     * Validates if the number passed as parameter has the allowed format. Numbers
     * entered by the user should be only either of the following two:
     * - A sequence of numbers without any additional characters (just minus sign for negative numbers)
     * Ex. 9999999 or -9999999
     * - A number in english format .i.e. a sequence of numbers that separates each thousand by commas.
     * Ex. 9,999,999 or -9,999,999
     * If none of the previous conditions are met false is returned
     *
     * @param number
     * @returns {boolean} True if the number is valid
     */
    isValidNumberFormat(number) {
        if (number === undefined || number === null || number.length === 0) {
            return false;
        }

        const commaIndex = number.indexOf(',');
        if (commaIndex >= 0) {
            const size = number.length;
            let validCommaIndex = size - 4;
            for (let i = size - 1; i >= 0; i--) {
                const character = number.charAt(i);
                if (character === '-') {
                    if (i === 0) {
                        return number.charAt(i + 1) !== ',';
                    }
                }
                if (i === validCommaIndex) {
                    if (character === ',' && i !== 0) {
                        validCommaIndex -= 4;
                    } else {
                        return false;
                    }
                } else {
                    if (isNaN(character)) {
                        return false;
                    }
                }
            }
        }

        return commaIndex >= 0 ? true : !isNaN(number);
    }

    /**
     * Defines the message displayed to the user when number entered is incorrect
     * @param validate Object stored in state that contains the output of validation in input parameters
     * @returns {string} The error message or empty string if there is none
     */
    getErrorMessage(validate) {
        let message;

        switch (validate.number) {
            case 'invalid-number-format':
                message = 'Incorrect number format';
                break;
            case  'invalid-number-size':
                message = 'Number is too big. Max allowed is +/- 9,999,999,999';
                break;
            default:
                message = '';
        }

        return message;
    }

    render() {
        const {item, validate} = this.state;

        const errorMessage = this.getErrorMessage(validate);

        return <div>
            <Container>
                <Form onSubmit={this.handleSubmit} className={"translator-form"}>
                    <InputGroup id="inputGroupNumber">
                        <Input type="text" name="number" id="number-input" value={item.number || ''}
                               valid={errorMessage === ''}
                               invalid={errorMessage !== ''}
                               onChange={this.handleChange} placeholder="Enter a number"/>
                        <FormFeedback id="formFeedBackNumber" invalid="true">{errorMessage}</FormFeedback>
                    </InputGroup>
                    <FormGroup>
                        <Button id="translate-button" color="primary" type="submit">Translate</Button>
                    </FormGroup>
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
