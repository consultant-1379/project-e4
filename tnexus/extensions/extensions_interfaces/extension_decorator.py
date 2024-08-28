from extension_chooser.program_matcher import is_program_in_focus
from functools import wraps


def pre_load(pre_load_func):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            print('RUNNING PRE LOAD')
            print(args)
            pre_load_func(*args)
            return func(*args, **kwargs)

        return wrapper

    return decorator


def extension(program):
    def decorator(func):
        func.extension = program

        def wrapper(*args, **kwargs):
            key, value = args
            print(value)
            print(f"Calling {func.__name__} with arguments: {args}, {kwargs} in extension: {func.extension}")
            while True:
                if is_program_in_focus(value):
                    func(*args, **kwargs)

        wrapper.extension = func.extension
        return wrapper

    return decorator


def post_load(program):
    def decorator(func):
        func.extension = program

        def wrapper(*args, **kwargs):
            print('post load')

        return wrapper

    return decorator
