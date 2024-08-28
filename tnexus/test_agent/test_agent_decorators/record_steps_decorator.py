import time


def record_steps(test_agent, action, delta):
    def decorator(func):
        def wrapper(*args, **kwargs):
            print(f"Calling {func.__name__} with arguments: {args}, {kwargs}, {action}")
            result = func(*args, **kwargs)
            if len(test_agent.get_steps()) <= 1000:
                test_agent.append(lambda: action(*result))
            else:
                test_agent.execute()
            print(test_agent)
            print(len(test_agent.get_steps()))
            time.sleep(delta)

        return wrapper

    return decorator
