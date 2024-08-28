import os

from keywords import EXTENSIONS
from tnexus_core.cli_handler.cli_handler import get_configuration_and_build_parser, \
    build_action_object_from_action_function
from tnexus_core.extension_handler.attach_extensions import prepare_extensions_for_being_ran


# def

def cli_config():
    return {
        'argument_parser': {
            'prog': 'tnexus, manual test automator',
            'description': 'Automate manual test cases, to be executed automatically, by building a robot framework '
                           'test, utilizing extensions for different pieces of software',
            'epilog': ''
        },

        'args_options': [
            {
                'args': ['-b', '--browser'],
                'kwargs': {
                    'dest': 'browser',
                    'nargs': 1,
                    'action': build_action_object_from_action_function(
                        lambda browser: browser),
                    'metavar': 'BROWSER_NAME',
                    'help': 'Attach a browser session'
                }
            },
            {
                'args': ['-t', '--terminal'],
                'kwargs': {
                    'dest': 'terminal',
                    'nargs': 1,
                    'action': build_action_object_from_action_function(
                        lambda terminal: terminal),
                    'metavar': 'TERMINAL_NAME',
                    'help': 'Attach a terminal'
                }
            }
        ]
    }


root = os.environ.get('TNEXUS_PATH')
# print(os.environ.get('DISPLAY'))
parser = get_configuration_and_build_parser(cli_config())
args = parser.parse_args()
dict_args = vars(args)
clean_args = [{label: program[0]} for label, program in zip(dict_args.keys(), dict_args.values())]
print(clean_args)
prepare_extensions_for_being_ran(os.path.join(root, EXTENSIONS), clean_args)
