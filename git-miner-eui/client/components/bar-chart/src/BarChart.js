/**
 * Component BarChart is defined as
 * `<e-bar-chart>`
 *
 * Imperatively create component
 * @example
 * let component = new BarChart();
 *
 * Declaratively create component
 * @example
 * <e-bar-chart></e-bar-chart>
 *
 * @extends {LitComponent}
 */
import { definition } from "@eui/component";
import { LitComponent, html } from "@eui/lit-component";
import style from "./barChart.css";
import * as d3 from "d3";
import file from "./data.json";

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition("e-bar-chart", {
  style,
  home: "bar-chart",
  props: {
    //propOne: { attribute: true, type: Boolean },
    //propTwo: { attribute: true, type: String, default: "Hello World" },
    buttonDisabled: { type: Boolean, default: false },
    name: { attribute: true, type: String, default: "" },
    data: { attribute: true, type: Array, default: []},
    YAxis: {},
    //averageData: {id: first, }
  },
})
export default class BarChart extends LitComponent {
  /**
   * Render the <e-bar-chart> component. This function is called each time a
   * prop changes.
   */
  //Get live data for barchart
  _getDataFromJson3() {
    fetch("http://localhost:8080/api/getInformationAboutRepo/barchart")
      .then((response) => response.json())
      .then((json) => {
        this.data2 = json.data;
      });
  }

  doGraph() {
    const json = this.data;
    var margin = { top: 10, right: 30, bottom: 90, left: 40 },
      width = 350 - margin.left - margin.right,
      height = 450 - margin.top - margin.bottom;

    const body = document.querySelector("body");
    const euiContainer = body.querySelector("eui-container");
    function findAllDeep(parent, selectors, depth = null) {
      let nodes = new Set();
      let currentDepth = 1;
      const recordResult = (nodesArray) => {
        for (const node of nodesArray) {
          nodes.add(node);
        }
      };
      const recursiveSeek = (_parent) => {
        // check for selectors in lightdom
        recordResult(_parent.querySelectorAll(selectors));
        if (_parent.shadowRoot) {
          // check for selectors in shadowRoot
          recordResult(_parent.shadowRoot.querySelectorAll(selectors));
          // look for nested components with shadowRoots
          for (let child of [
            ..._parent.shadowRoot.querySelectorAll("*"),
          ].filter((i) => i.shadowRoot)) {
            // make sure we haven't hit our depth limit
            if (depth === null || currentDepth < depth) {
              recursiveSeek(child);
            }
          }
        }
      };
      recursiveSeek(parent);
      const [first] = nodes;
      first.innerHTML = "";
      return first;
    }
    var local = findAllDeep(euiContainer, `[id="${this.name}"]`, 20); //getShadowRoot(eDependencyGraphComponent, "#my_dataviz")
    var svg = d3
      .select(local)
      .append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
      .append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    function createChart(data2) {
      // X axis
      var x = d3
        .scaleBand()
        .range([0, width])
        .domain(
          data2.map(function (d) {
            return d.Word;
          })
        )
        .padding(0.2);
      svg
        .append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x))
        .selectAll("text")
        .attr("transform", "translate(-10,0)rotate(-45)")
        .style("text-anchor", "end");
      // this.YAxis = 13000;
      // Add Y axis
      
      
      const ids = data2.map(object => {
        return parseInt(object.Value);
      });

      const max = Math.max(...ids);
      var y = d3.scaleLinear().domain([0, max + 20]).range([height, 0]);
      svg.append("g").call(d3.axisLeft(y));

      // Bars
      
      svg
        .selectAll("mybar")
        .data(data2)
        .enter()
        .append("rect")
        .attr("x", function (d) {
          return x(d.Word);
        })
        .attr("y", function (d) {
          return y(d.Value);
        })
        .attr("width", x.bandwidth())
        .attr("height", function (d) {
          return height - y(d.Value);
        })
        .attr("fill", "#1174e6");
    }

    createChart(json);

    // this.executeRender();
  }

  didRender() {
    console.log("I AM BEING PRINTED OUT", this.id);
    this.doGraph(this.id);
    console.log("IN THE RENDER", this.name, this.data)
  }


  _onEventHandler(event, value) {
    if (this.value == false) {
      this.buttonDisabled = true;
    }
  }
  render() {
    return html` <div slot="content" id="${this.name}"></div> `;
  }
}

/**
 * Register the component as e-bar-chart.
 * Registration can be done at a later time and with a different name
 */
BarChart.register();
