import TranslationHistory from './TranslationHistory';
import React from 'react'
import Adapter from 'enzyme-adapter-react-16'
import {configure} from 'enzyme';
import {shallow} from 'enzyme';

configure({adapter: new Adapter()});

it('renders Translation history without registers', () => {
    const tree = shallow(
        <TranslationHistory/>,
    );

    expect(tree).toMatchSnapshot();
});

it('renders Translation history with register', (done) => {
    const mockSuccessResponse = [
        {
            "number": -1,
            "translation": "Negative one"
        },
        {
            "number": 20,
            "translation": "Twenty"
        },
        {
            "number": 999,
            "translation": "Nine hundred and ninety nine"
        }
    ];

    const mockJsonResponse = Promise.resolve(mockSuccessResponse);
    const mockFetchPromise = Promise.resolve({
        json: () => mockJsonResponse,
    });
    jest.spyOn(global, 'fetch').mockImplementation(() => mockFetchPromise);

    const wrapper = shallow(
        <TranslationHistory/>
    );

    const button = wrapper.find('#history-button');
    button.simulate('click', {
        preventDefault() {
        }
    });

    process.nextTick(() => {
        expect(wrapper).toMatchSnapshot();
        done();
    });
});
