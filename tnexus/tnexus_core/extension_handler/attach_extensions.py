import os

from extension_chooser.program_matcher import is_program_in_program_list
from run_extensions import run_multiple_extensions, get_extension_instance_experimental
from misc.keywords import PYTHON_POSTFIX, EXTENSIONS, PACKAGE
from extensions.extension_chooser.choose_extensions_per_program import choose_extension_based_on_program, \
    is_extension_with_label


def prepare_extensions_for_being_ran(directory, program_args):
    extensions = []
    for file_name in os.listdir(directory):
        if not file_name.__contains__(PACKAGE) and file_name.endswith(PYTHON_POSTFIX):
            file_path = os.path.join(directory, file_name)
            for program_arg in program_args:
                if is_program_in_program_list(program_arg):
                    if is_extension_with_label(program_arg, file_path):
                        extensions.append((program_arg, file_path))
    run_multiple_extensions(extensions)
