/**
 * Integration tests for <e-bar-chart>
 */
import { expect } from 'chai';
import '../BarChart';
import {
  inShadow,
  injectHTMLElement,
  simulateEvent,
  nextTick
} from '../../../../../test/utils';

describe('BarChart Component Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic component setup', () => {
      it('should create a new <e-bar-chart>', async () => {
        const customElement = await inject('<e-bar-chart></e-bar-chart>');
        // check shadow DOM
        const headingTag = inShadow(customElement, 'h1');
        expect(headingTag.textContent, '"Your component markup goes here" was not found').to.equal('Your component markup goes here');
      });
    });
});
