import App from './App';
import React from 'react'
import renderer from 'react-test-renderer';

it('renders App', () => {
    const tree = renderer.create(
        <App/>,
    ).toJSON();
    expect(tree).toMatchSnapshot();
});
