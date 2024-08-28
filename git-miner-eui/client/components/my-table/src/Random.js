// /**
//  * Component Job9MainComponent is defined as
//  * `<e-job9-main-component>`
//  *
//  * Imperatively create component
//  * @example
//  * let component = new Job9MainComponent();
//  *
//  * Declaratively create component
//  * @example
//  * <e-job9-main-component></e-job9-main-component>
//  *
//  * @extends {LitComponent}
//  */
//  import { definition } from '@eui/component';
//  import { LitComponent, html } from '@eui/lit-component';
//  import style from './chartComponent.css';
//  import * as d3 from 'd3';
//  import file from './data.json';
//  /**
//   * @property {Boolean} propOne - show active/inactive state.
//   * @property {String} propTwo - shows the "Hello World" string.
//   */
//  @definition('e-chart-component', {
//    style,
//    home: 'chart-component',
//    props: {
//      propOne: { attribute: true, type: Boolean },
//      propTwo: { attribute: true, type: String, default: 'Hello World' },
//    },
//  })
//  export default class ChartComponent extends LitComponent {
//    // constructor(){
//    //   super();
//    //   this.doGraph();
//    // }
//    doGraph() {
//      const json = file;
//      var margin = { top: 10, right: 30, bottom: 30, left: 60 },
//        width = 460 - margin.left - margin.right,
//        height = 400 - margin.top - margin.bottom;
//      const body = document.querySelector('body')
//      const euiContainer = body.querySelector('eui-container')
//      function findAllDeep(parent, selectors, depth = null) {
//        let nodes = new Set();
//        let currentDepth = 1
//          ;
//        const recordResult = (nodesArray) => {
//          for (const node of nodesArray) {
//            nodes.add(node)
//          }
//        }
//        const recursiveSeek = _parent => {
//          // check for selectors in lightdom
//          recordResult(_parent.querySelectorAll(selectors));
//          if (_parent.shadowRoot) {
//            // check for selectors in shadowRoot
//            recordResult(_parent.shadowRoot.querySelectorAll(selectors));
//            // look for nested components with shadowRoots
//            for (let child of [..._parent.shadowRoot.querySelectorAll('*')].filter(i => i.shadowRoot)) {
//              // make sure we haven't hit our depth limit
//              if (depth === null || currentDepth < depth) {
//                recursiveSeek(child);
//              }
//            }
//          }
//        };
//        recursiveSeek(parent);
//        const [first] = nodes
//        return first;
//      };
//      var local = findAllDeep(euiContainer, `[id="my_dataviz"]`, 20); //getShadowRoot(eDependencyGraphComponent, "#my_dataviz")
//      var svg = d3.select(local)
//        .append("svg")
//        .attr("width", width + margin.left + margin.right)
//        .attr("height", height + margin.top + margin.bottom)
//        .append("g")
//        .attr("transform",
//          "translate(" + margin.left + "," + margin.top + ")");
//      function createChart (data) {
//        var sumstat = d3.nest() // nest function allows to group the calculation per level of a factor
//          .key(function (d) { return d.name; })
//          .entries(data);
//        // Add X axis --> it is a date format
//        var x = d3.scaleLinear()
//          .domain(d3.extent(data, function (d) { return d.year; }))
//          .range([0, width]);
//        svg.append("g")
//          .attr("transform", "translate(0," + height + ")")
//          .call(d3.axisBottom(x).ticks(5));
//        // Add Y axis
//        var y = d3.scaleLinear()
//          .domain([0, d3.max(data, function (d) { return +d.n; })])
//          .range([height, 0]);
//        svg.append("g")
//          .call(d3.axisLeft(y));
//        // color palette
//        var res = sumstat.map(function (d) { return d.key }) // list of group names
//        var color = d3.scaleOrdinal()
//          .domain(res)
//          .range(['#e41a1c', '#377eb8', '#4daf4a', '#984ea3', '#ff7f00', '#ffff33', '#a65628', '#f781bf', '#999999'])
//        // Draw the line
//        svg.selectAll(".line")
//          .data(sumstat)
//          .enter()
//          .append("path")
//          .attr("fill", "none")
//          .attr("stroke", function (d) { return color(d.key) })
//          .attr("stroke-width", 1.5)
//          .attr("d", function (d) {
//            return d3.line()
//              .x(function (d) { return x(d.year); })
//              .y(function (d) { return y(+d.n); })
//              (d.values)
//          })
//      }
//      createChart(json)
//      // this.executeRender();
//    }
//    render() {
//      // console.log('GRAPH',this.showGraph)
//      return html`<eui-base-v0-button @click=${this.doGraph}>Click</eui-base-v0-button>
//  <div slot="content" id="my_dataviz"></div>`;
//    }
//  }
//  //  <eui-base-v0-button @click=${this._loadGraph}>Click</eui-base-v0-button>
//  //       <div  slot="content"id="my_dataviz"></div>
//  ChartComponent.register();
