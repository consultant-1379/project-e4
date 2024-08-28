from tnexus_core.extension_handler.run_extensions import get_extension_instance_experimental
from extension_chooser.program_matcher import is_program_in_program_list, get_extension_labels_based_on_program, \
    get_extension_label_based_on_program


def is_extension_with_label(program_arg, extension_path):
    extension = get_extension_instance_experimental(extension_path)
    (program_key, program_value) = next(iter(program_arg.items()))
    return extension.extension == program_key


def attach_cli_args_to_decorator():
    pass


def choose_extension_based_on_program(program_arg, extension_path):
    print('running')
    if is_program_in_program_list(program_arg):
        print('Program in list, gathering extensions')
        extension = get_extension_instance_experimental(extension_path)
        if is_extension_with_label(program_arg, extension):
            return extension
