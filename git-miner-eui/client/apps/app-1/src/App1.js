/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
import { definition } from "@eui/component";
import { App, html } from "@eui/app";
import style from "./app1.css";
import "@eui/table";
import "@eui/layout";
import "whatwg-fetch";
import { vega } from "./spec.js";
import "@eui/chart";
import "bar-chart";

//import 'my-table';

@definition("e-app-1", {
  style,
  props: {
    response: { attribute: false },
    data: { attribute: false, type: Array, default: [] },
    columns: { attribute: false, type: Array, default: [] },
    data2: { attribute: false, type: Array, default: [] },
    columns2: { attribute: false, type: Array, default: [] },
    propOne: { attribute: true, type: Boolean, default: true },
    data3: { attribute: false, type: Array, default: [] },
    columns3: { attribute: false, type: Array, default: [] },
    data4: { attribute: false, type: Array, default: [] },
    columns4: { attribute: false, type: Array, default: [] },
    data5: { attribute: false, type: Array, default: [] },
    columns5: { attribute: false, type: Array, default: [] },
    data6: { attribute: false, type: Array, default: [] },
    columns6: { attribute: false, type: Array, default: [] },
    data7: { attribute: false, type: Array, default: [] },
    columns7: { attribute: false, type: Array, default: [] },
    data8: { attribute: false, type: Array, default: [] },
    columns8: { attribute: false, type: Array, default: [] },
    date: { type: String },
    max: { type: String },
    min: { type: String },
    commitHash: { type: String },
    dummyData: { type: Object },
    startDate: { type: String },
    endDate: { type: String },
    buttonDisabled: { type: Boolean, default: true },
    hashButtondisabled: { type: Boolean, default: true },
    commitHashInput: { type: String },
    globalRow: { type: String },
    commitHashTable: { type: Object },
    chartData: { type: Object },
    average: {type: Object},
    changeSet: {type: Object}
  },
})
export default class App1 extends App {
  // Uncomment this block to add initialization code
  // constructor() {
  //   super();
  //   // initialize
  // }

  handleEvent(event) {
    if (event.type === "click") {
      // handle the click event.
    }
  }

  /**
   * Render the <e-app-1> app. This function is called each time a
   * prop changes.
   */
  _getDataFromJson() {
    fetch(`http://localhost:8080/api/getCommitTable/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        this.columns = json.columns;
        this.columns5 = [
          { title: "Commit Hash", attribute: "hash", sortable: true },
        ];
        this.data = json.data;
      });
  }
  _getDataFromJson2() {
    fetch(`http://localhost:8080/api/getRepoTable/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        this.columns2 = json.columns;
        console.log(this.columns);
        this.data2 = json.data;
        console.log(this.data);
      });
  }

  _getDataFromJson3() {
    fetch(`http://localhost:8080/api/getChangeSet/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
        this.data4 = json;
      });
  }
  //Get List of Hashes
  _getDataFromJson4() {
    fetch(`http://localhost:8080/api/getStatsRepository/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
       this.data6 = json;
      });
  }

  _getDataFromJson5() {
    fetch(`http://localhost:8080/api/getCodeChurnAndHunksCount/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        this.columns7 = json.columns;
        this.data7 = json.data;
        //this.data5 = this.data.map((commit) => commit.col1);
        console.log("THIS IS THE HUNKS COUNT INFO", json);
      });
  }

  _getDataFromJson6() {
    fetch(`http://localhost:8080/api/getContributorsCount/${"eric-oss-adc-app-engineering"}?from=${this.startDate}&to=${this.endDate}`)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        this.columns8 = json.columns;
        console.log(this.columns);
        this.data8 = json.data;
        //this.data5 = this.data.map((commit) => commit.col1);
        console.log("THIS IS THE HUNKS COUNT INFO");
      });
  }

  _onChangeHandler(event, value) {
    this.startDate = value;
    this._finalChange();
    fetch("http://localhost:8080/api/", {
      Method: "POST",
      Headers: {
        Accept: "application.json",
        "Content-Type": "application/json",
      },
      Body: body,
      Cache: "default",
    })
      .then((response) => response.json())
      .then((json) => {
        return this.startDate;
      });
  }
  _onChangeHandler2(event, value) {
    this.endDate = value;
    this._finalChange();
    fetch("http://localhost:8080/api/", {
      Method: "POST",
      Headers: {
        Accept: "application.json",
        "Content-Type": "application/json",
      },
      Body: body,
      Cache: "default",
    })
      .then((response) => response.json())
      .then((json) => {
        return this.endDate;
      });
  }

  _finalChange() {
    //if(this.endDate == "undefined")
    // fetch('http://localhost:8080/api/', {
    //   Method: 'POST',
    //   Headers: {
    //     Accept: 'application.json',
    //     'Content-Type': 'application/json'
    //   },
    //   Body: body,
    //   Cache: 'default'
    // })
    // .then((response) => response.json())
    // .then((json) => {
    //   return this.startDate +"," + this.endDate;
    // });
    if (this.startDate.length > 0 && this.endDate.length > 0) {
      this.buttonDisabled = false;
    }
  }

  _moduleTable(event) {
    console.log(event);
  }

  _switchButton(value) {
    this.commitHashInput = value;
    if (value.length > 0) {
      this.hashButtondisabled = false;
    } else {
      this.hashButtondisabled = true;
    }
  }

  _getHashOnClick() {
    console.log(this.commitHashInput);
    fetch(`http://localhost:8080/api/getHashTable/${this.commitHashInput}`)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        this.columns3 = json.columns;
        console.log(this.columns3);
        this.data3 = json.data;
        console.log(this.data3);
        this.commitHashTable = html`
        <eui-table-v0
          slot="content"
          virtual-scroll
          .columns=${this.columns3}
          .data=${this.data3}
          sortable
          virtual-scroll
        ></eui-table-v0>
        </div>`;
      });
  }

  requestData() {
    ///c
    console.log("firing");
    fetch(`http://localhost:8080/api/generateRepoData?repository=${"eric-oss-adc-app-engineering"}&from=${this.startDate}&to=${this.endDate}`) .then((response) => response.json())
    .then((json) => {
      if(json === true) {
        this._getDataFromJson();
        //this._getDataFromJson2();
        this._getDataFromJson3();
        this._getDataFromJson4();
        this._getDataFromJson5();
        this._getDataFromJson6();
      }
    });
  }

  didConnect() {
    this._getDataFromJson();
    this.average = html`
    <e-bar-chart name="first" data=${[]}></e-bar-chart>
    `

    this.changeSet = html`
    <e-bar-chart name="second" data=${[]}></e-bar-chart>
    `
    // this._getDataFromJson2();
  }

  render() {
    const { EUI } = window;
    const columns5 = [{ title: "Hash", attribute: "col1", sortable: true }];
    return html`
    <div class="layout__dashboard">
      <eui-layout-v0-tile tile-title="Commit and Repo data" column=0 column-span=2 >
        
        <div slot="content">
          <eui-layout-v0-tabs>
          <eui-layout-v0-tab selected>Commit Table</eui-layout-v0-tab>
          <eui-layout-v0-tab>Repo Table</eui-layout-v0-tab>
          <eui-layout-v0-tab>Hunks Count</eui-layout-v0-tab>
          
          <div slot="content" selected>
            
            <div slot="content">
              <eui-base-v0-datepicker slot="content" placeholder="Choose start Date"
                @eui-datepicker:change="${(event) =>
                  this._onChangeHandler(event, event.detail.date)}">
              </eui-base-v0-datepicker>
              <eui-base-v0-datepicker slot="content" title="Choose start Date"
              @eui-datepicker:change="${(event) =>
                this._onChangeHandler2(event, event.detail.date)}">
              </eui-base-v0-datepicker>            
              <eui-base-v0-button icon="calendar" ?disabled=${
                this.buttonDisabled
              } @click=${() => this.requestData()} tabindex="+1">Submit</eui-base-v0-button>  
            </div>
            


            <eui-layout-v0-tile tile-title="Commit Table">
              <eui-table-v0
                slot="content"
                style="height: 300px"
                expanded-row-height="150px"
                .columns=${this.columns}
                .data=${this.data}
                sortable
                virtual-scroll}}
              ></eui-table-v0>
            </eui-layout-v0-tile> 
          </div>

        <div slot="content">
        <eui-layout-v0-tile tile-title="Repo Table">
          <div slot="content">
          
        </eui-base-v0-datepicker>
          <eui-table-v0
          .columns=${this.columns8}
          .data=${this.data8}
          sortable
        ></eui-table-v0>
          </eui-layout-v0-tile>
          </div>
        </div>


        <div slot="content"> 
        <eui-layout-v0-tile slot="content" tile-title="Hunks Count, Change Set and Code Churn">
          <div slot="content">
          <eui-table-v0
          .columns=${this.columns7}
          .data=${this.data7}
          sortable
          virtual-scroll
        ></eui-table-v0>
          </div>
          <eui-table-v0
                slot="content"
                style="height: 300px"
                expanded-row-height="150px"
                .columns=${this.columns}
                .data=${this.data}
                sortable
                virtual-scroll}}
              ></eui-table-v0>
        </eui-layout-v0-tile>
        </div>
        </eui-layout-v0-tabs>
        </div>
      </eui-layout-v0-tile>
      <eui-layout-v0-tile tile-title="Bar chart" >
        
        <div slot="content">
        <eui-layout-v0-tile >
            <div slot="content" >
            <eui-layout-v0-tabs>
            <eui-layout-v0-tab selected>Change Set</eui-layout-v0-tab>
          <eui-layout-v0-tab>Stats Repository</eui-layout-v0-tab>
          <div slot="content" selected>

            <div slot="content">        
            <e-bar-chart name="first" .data=${this.data4}></e-bar-chart>
            </div>
          </div>
          <div slot="content">
          <e-bar-chart name="second" .data=${this.data6}></e-bar-chart>
          </div>
          </eui-layout-v0-tabs>
            </div>
        </eui-layout-v0-tile>
          
        </div>
      </eui-layout-v0-tile>
      <eui-layout-v0-tile tile-title="List Of Hash" column=0 >
        <div slot="content">

        <div slot="content">
        <eui-table-v0
              slot="content"
              style="height: 300px" expanded-row-height="100px" tiny single-select sortable virtual-scroll compact dashed
              .columns=${this.columns5}
              .data=${this.data}
              }}
            ></eui-table-v0>
        </div>
        </div>
      </eui-layout-v0-tile>
      <eui-layout-v0-tile tile-title="Choose Your Hash" column=0 column-span=2>
        <div slot="content">
        <eui-base-v0-text-field name="search" autocomplete placeholder="Enter Commit Hash" @input=${(
          event
        ) => this._switchButton(event.detail.value)}></eui-base-v0-text-field>
        <eui-base-v0-button ?disabled=${this.hashButtondisabled} 
        tabindex="-1" @click=${() => this._getHashOnClick()}>
          Submit
        </eui-base-v0-button>
        <!-- <eui-base-v0-dropdown label="label" data-type="multi">
          </eui-base-v0-dropdown> -->
        ${this.commitHashTable}
        </div>
      </eui-layout-v0-tile>
    </div>
    `;
  }
}
/**
 * Register the component as e-app-1.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
App1.register();
