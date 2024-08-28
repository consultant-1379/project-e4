/**
 * Component MyTable is defined as
 * `<e-my-table>`
 *
 * Imperatively create component
 * @example
 * let component = new MyTable();
 *
 * Declaratively create component
 * @example
 * <e-my-table></e-my-table>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './myTable.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-my-table', {
  style,
  home: 'my-table',
  props: {
    data: {type: Object, default: {} },
    propOne: { attribute: true, type: Boolean },
    propTwo: { attribute: true, type: String, default: 'Hello World' },
  },
})
export default class MyTable extends LitComponent {
  /**
   * Render the <e-my-table> component. This function is called each time a
   * prop changes.
   */
  render() {
    return html`<h1>Your component markup goes here</h1>
    <h2>props</h2>
      prop-one: ${this.propOne}
    <p>
      prop-two: ${this.propTwo}
      <eui-table-v0 .columns=${columns} .data=${data}></eui-table-v0>`;
  }
}

/**
 * Register the component as e-my-table.
 * Registration can be done at a later time and with a different name
 */
MyTable.register();
