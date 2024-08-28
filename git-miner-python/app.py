from flask import Flask
from markupsafe import escape
from flask import request
from flask import json
import data_miner
import os

app = Flask(__name__)

# ?repository=mining&date_from=10&date_to=test
# ?repository=eric-oss-adc-app-engineering&date_from=2021-09-13&date_to=2021-11-04


@app.route('/repo')
def repo():
    repository_name = request.args.get('repository')
    date_from = request.args.get('date_from')
    date_to = request.args.get('date_to')

    valid = data_miner.process(escape(repository_name),
                               escape(date_from), escape(date_to))

    if (valid):
        return json.dumps(True)
    else:
        return json.dumps(False)


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


if __name__ == "__main__":
    port = int(os.environ.get('PORT', 5000))
    app.run(debug=True, host='0.0.0.0', port=port)
