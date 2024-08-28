import subprocess

program_matches = {
    'browser': ('google-chrome', 'google', 'chrome', 'firefox', 'fox', 'microsoft-edge', 'edge', 'chromium'),
    'terminal': ('terminator', 'xterm', 'Terminal')
}


def is_program_in_program_list(program):
    (program_key, program_value) = next(iter(program.items()))
    program_pairs = program_matches[program_key]
    return program_value in program_pairs


def get_extension_labels_based_on_program(programs):
    labels = []
    for program in programs:
        labels.append(get_extension_label_based_on_program(program))
    return labels


def get_extension_label_based_on_program(program):
    for program_match in program_matches:
        label, program_name = program_match
        if program in program_name:
            return label


def is_program_in_focus(program_name):
    try:
        result = subprocess.run(["WINDOW_ID=$(xprop -root _NET_ACTIVE_WINDOW | awk '{print $5}'); WINDOW_PID=$(xprop "
                                 "-id $WINDOW_ID | awk '/_NET_WM_PID/ {print $3}'); WINDOW_NAME=$(ps -p $WINDOW_PID "
                                 "-o comm=); echo $WINDOW_NAME"], capture_output=True, text=True, check=True,
                                shell=True).stdout
        return program_name.lower() in result.lower()
    except subprocess.CalledProcessError as e:
        print('Error: ', e)
        return False
