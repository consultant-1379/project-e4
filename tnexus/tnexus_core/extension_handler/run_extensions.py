from concurrent.futures import ThreadPoolExecutor
from os.path import basename, dirname
import importlib


def run_multiple_extensions(extensions):
    extension_instances = []
    for arg, extension_path in extensions:
        (program_key, program_value) = next(iter(arg.items()))
        print(f'EXTENSION PATH {extension_path}')
        print(f'ARG PAIR {program_key}, {program_value}')
        extension_instances.append((get_extension_instance_experimental(extension_path), (program_key, program_value)))

    with ThreadPoolExecutor() as executor:
        [executor.submit(extension_instance, *args) for extension_instance, args in extension_instances]


def get_extension_instance(extension_path):
    file_name = basename(extension_path)
    py_mod = importlib.import_module(file_name[:-3], dirname(extension_path))
    extension_class = basename(file_name[:-3]).replace('_', ' ').title().replace(' ', '').__str__()
    return getattr(py_mod, extension_class)


def get_extension_instance_experimental(extension_path):
    file_name = basename(extension_path)
    py_mod = importlib.import_module(file_name[:-3], dirname(extension_path))
    extension_function = file_name[:-3]
    return getattr(py_mod, extension_function)
