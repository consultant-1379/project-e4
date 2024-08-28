import argparse
from argparse import ArgumentParser


def build_action_object_from_action_function(action_function):
    class TemplateAction(argparse.Action):
        def __init__(self, option_strings, dest, nargs=None, **kwargs):
            super().__init__(option_strings, dest, **kwargs, nargs=nargs)

        def __call__(self, parser, namespace, values, option_string=None):
            setattr(namespace, self.dest, action_function(values))

    return TemplateAction


def _build_argument_functions(parser, args_options):
    for option in args_options:
        parser.add_argument(*option['args'], **option['kwargs'])
    return parser


def get_configuration_and_build_parser(config):
    parser = ArgumentParser(**config['argument_parser'])
    parser = _build_argument_functions(parser, config['args_options'])
    return parser
