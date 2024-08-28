/**
 * Integration tests for <e-chart-legend>
 */
import { expect } from 'chai';
import '../ChartLegend';
import {
  inShadow,
  injectHTMLElement,
  simulateEvent,
  nextTick
} from '../../../../../test/utils';

describe('ChartLegend Component Tests', () => {
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
      it('should create a new <e-chart-legend>', async () => {
        const customElement = await inject('<e-chart-legend></e-chart-legend>');
        // check shadow DOM
        const headingTag = inShadow(customElement, 'h1');
        expect(headingTag.textContent, '"Your component markup goes here" was not found').to.equal('Your component markup goes here');
      });
    });
});
