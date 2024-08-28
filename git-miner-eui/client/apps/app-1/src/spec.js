export const vega = {
  $schema: "https://vega.github.io/schema/vega/v4.json",
  width: 300,
  height: 300,
  padding: 5,
  config: {
    axis: {
      labelFont: "'Ericsson Hilda', 'Helvetica'",
      titleFont: "'Ericsson Hilda', 'Helvetica'",
      labelFontSize: 12,
      labelColor: "#242424",
    },
  },
  data: [
    {
      name: "table",
      values: [
        { category: "critical", value: 9 },
        { category: "major", value: 3 },
        { category: "minor", value: 2 },
        { category: "warning", value: 3 },
        { category: "indeterminate", value: 2 },
        { category: "cleared", value: 3 },
      ],
    },
  ],

  scales: [
    {
      name: "yscale",
      type: "band",
      domain: { data: "table", field: { signal: "y-axis" } },
      range: "height",
      padding: 0.2,
    },
    {
      name: "xscale",
      type: "linear",
      domain: { data: "table", field: { signal: "x-axis" } },
      range: "width",
      round: true,
      zero: true,
    },
  ],

  axes: [
    {
      orient: "left",
      scale: "yscale",
      tickSize: 1,
      labelPadding: 4,
      zindex: 1,
      title: "Alarm Status",
    },
    {
      orient: "bottom",
      scale: "xscale",
      tickSize: 1,
      labelPadding: 4,
      title: "x-axis",
    },
  ],
  signals: [
    {
      name: "tooltip",
      value: {},
      on: [
        { events: "rect:mouseover", update: "datum" },
        { events: "rect:mouseout", update: "{}" },
      ],
    },
    {
      name: "y-axis",
      value: "category",
    },
    {
      name: "x-axis",
      value: "value",
    },
  ],
  marks: [
    {
      name: "bars",
      from: { data: "table" },
      type: "rect",
      encode: {
        enter: {
          y: { scale: "yscale", field: "category" },
          height: { scale: "yscale", band: 1 },
          x: { scale: "xscale", field: "value" },
          x2: { scale: "xscale", value: 0 },
          fill: [
            { test: "datum.category == 'critical'", value: "#F52323" },
            { test: "datum.category == 'major'", value: "#F78521" },
            { test: "datum.category == 'minor'", value: "#F2C618" },
            { test: "datum.category == 'warning'", value: "#0082F0" },
            { test: "datum.category == 'indeterminate'", value: "#767676" },
            { test: "datum.category == 'cleared'", value: "#36B07F" },
            { value: "steelblue" },
          ],
        },
      },
    },
    {
      type: "text",
      from: { data: "bars" },
      encode: {
        enter: {
          fill: { value: "#242424" },
          align: { value: "right" },
          baseline: { value: "middle" },
        },
        update: {
          x: { scale: "xscale", signal: "tooltip.value", offset: -5 },
          y: {
            scale: "yscale",
            signal: "tooltip.category",
            offset: { field: "height", mult: 0.5 },
          },
          text: { signal: "tooltip.value" },
        },
      },
    },
  ],
};
