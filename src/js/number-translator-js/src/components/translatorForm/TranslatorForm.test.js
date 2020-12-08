import TranslatorForm from './TranslatorForm';
import React from 'react'
import Adapter from 'enzyme-adapter-react-16'
import {configure} from 'enzyme';
import {shallow} from 'enzyme';

configure({adapter: new Adapter()});

it('renders initial form', () => {
    const wrapper = shallow(
        <TranslatorForm/>,
    );

    expect(wrapper).toMatchSnapshot();
});

it('validates number format', () => {
    const wrapper = shallow(
        <TranslatorForm/>,
    );
    const instance = wrapper.instance();

    expect(instance.isValidNumberFormat(null)).toBe(false);
    expect(instance.isValidNumberFormat(undefined)).toBe(false);
    expect(instance.isValidNumberFormat("")).toBe(false);

    expect(instance.isValidNumberFormat("words")).toBe(false);
    expect(instance.isValidNumberFormat("wordsAnd13413")).toBe(false);
    expect(instance.isValidNumberFormat("w8r4ik4-,")).toBe(false);
    expect(instance.isValidNumberFormat("1,333,34")).toBe(false);
    expect(instance.isValidNumberFormat(",333,34")).toBe(false);
    expect(instance.isValidNumberFormat("-,333,34")).toBe(false);
    expect(instance.isValidNumberFormat("333-34")).toBe(false);
    expect(instance.isValidNumberFormat("33334-")).toBe(false);
    expect(instance.isValidNumberFormat("33334-")).toBe(false);
    expect(instance.isValidNumberFormat(",")).toBe(false);
    expect(instance.isValidNumberFormat("-,")).toBe(false);
    expect(instance.isValidNumberFormat("-")).toBe(false);
    expect(instance.isValidNumberFormat("000010")).toBe(true);
    expect(instance.isValidNumberFormat("-0010")).toBe(true);
    expect(instance.isValidNumberFormat("0000")).toBe(true);
    expect(instance.isValidNumberFormat("-20")).toBe(true);
    expect(instance.isValidNumberFormat("200")).toBe(true);
});
