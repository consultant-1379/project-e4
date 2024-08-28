from time import sleep
from pydriller import Repository
from pydriller.metrics.process.change_set import ChangeSet
from pydriller.metrics.process.code_churn import CodeChurn
from pydriller.metrics.process.contributors_count import ContributorsCount
from pydriller.metrics.process.hunks_count import HunksCount
from datetime import datetime
import pandas as pd
import os


path_global = './repos'
commit_info = []
modified_info = []
commit_added_lines = []
contributors = []
contributors_stats = []
repository_avg_added_lines_commit = 0
repository_max_added_lines_commit = 0
commit_amounts = 0
commit_avg_contribution = 0
avg_cont = 0
code_churn = []
contributors_count = []
hunks_count = []


def process(repo_name, date_from, date_to):
    '''
    # Between 2 dates - for the general query
    dt1 = datetime(2016, 10, 8, 17, 0, 0)
    dt2 = datetime(2016, 10, 8, 17, 59, 0)

    # Between 2 dates - for the metric queries
    since=datetime(2019, 1, 1),
    to=datetime(2019, 12, 31))

    '''

    if not os.path.isdir(f'./data/{repo_name}{date_from}{date_to}'):
        os.makedirs(f'./data/{repo_name}{date_from}{date_to}')

    date_from_array = date_from.split("-")
    date_to_array = date_to.split("-")

    year_from = int(date_from_array[0])
    month_from = int(date_from_array[1])
    day_from = int(date_from_array[2])

    year_to = int(date_to_array[0])
    month_to = int(date_to_array[1])
    day_to = int(date_to_array[2])

    dt1 = datetime(
        year_from, month_from, day_from, 17, 0, 0)
    dt2 = datetime(year_to, month_to,
                   day_to, 17, 59, 0)

    dt1_metric = datetime(
        year_from, month_from, day_from)
    dt2_metric = datetime(year_to, month_to,
                          day_to)

    path_global = './repos/' + repo_name

    valid = False

    try:
        def get_avg(data):
            total_value = 0
            count = 0
            for x in data:
                total_value += x
                count += 1

            avg_value = total_value/count

            return avg_value

        def get_highest(data):
            highest = 0
            for x in data:
                if highest > x:
                    highest = highest
                else:
                    highest = x

            return highest

        def generate_data():
            global avg_cont
            for commit in Repository(path_global, since=dt1, to=dt2).traverse_commits():
                commit_info.append({'Project Name': commit.project_name, 'Hash': commit.hash, 'Date': commit.committer_date,
                                    'Author': commit.author.name, 'Email': commit.author.email, 'Message': commit.msg, 'Insertions': commit.insertions,
                                    'Deletions': commit.deletions})

                for file in commit.modified_files:
                    modified_info.append({'Hash': commit.hash, 'File': file.filename,
                                          'Added Lines': file.added_lines, 'Deleted Lines': file.deleted_lines})
                    avg_cont += 1

        generate_data()

        df = pd.DataFrame(commit_info)

        # Change Set
        metric = ChangeSet(path_to_repo=path_global,
                           since=dt1_metric,
                           to=dt2_metric)
        maximum = metric.max()
        average = metric.avg()

        change_set = [[maximum, average]]
        columns = ['Maximum', 'Average']
        df_change_set = pd.DataFrame(change_set, columns=columns)
        df_change_set.to_csv(
            f'./data/{repo_name}{date_from}{date_to}/change_set.csv', index=False)

        # Hunks count
        metric = HunksCount(path_to_repo=path_global,
                            since=dt1_metric,
                            to=dt2_metric)
        files = metric.count()

        for item in files.keys():
            hunks_count.append(files[item])

        # Code Churn
        metric = CodeChurn(path_to_repo=path_global,
                           since=dt1_metric,
                           to=dt2_metric, add_deleted_lines_to_churn='a')

        files_count = metric.count()
        files_max = metric.max()
        files_avg = metric.avg()

        list_files_count = []
        list_files_max = []
        list_files_avg = []

        for item in files_count.keys():
            list_files_count.append([item, files_count[item]])

        for values in files_max.keys():
            list_files_max.append([values, files_max[values]])

        for average in files_avg.keys():
            list_files_avg.append([average, files_avg[average]])

        for i in range(len(list_files_count)):
            code_churn.append([list_files_count[i][0], list_files_count[i][1],
                               list_files_max[i][1], list_files_avg[i][1], hunks_count[i]])

        columns = ['Filename', 'Count', 'Max', 'Avg', 'Hunks Count']
        df_code_churn = pd.DataFrame(code_churn, columns=columns)
        df_code_churn.to_csv(
            f'./data/{repo_name}{date_from}{date_to}/code_churn.csv', index=False)

        # Contributors count
        metric = ContributorsCount(path_to_repo=path_global,
                                   since=dt1_metric,
                                   to=dt2_metric)

        count = metric.count()
        minor = metric.count_minor()

        list_count = []
        list_minor = []

        for item in count.keys():
            list_count.append([item, count[item]])

        for number in minor.keys():
            list_minor.append([number, minor[number]])

        for i in range(len(list_count)):
            contributors_count.append(
                [list_count[i][0], list_count[i][1], list_minor[i][1]])

        columns = ['File', 'Contributions Count', 'Minor']
        df_contributors_count = pd.DataFrame(
            contributors_count, columns=columns)
        df_contributors_count.to_csv(
            f'./data/{repo_name}{date_from}{date_to}/contributors_count.csv', index=False)

        df_two = pd.DataFrame(modified_info)
        df_contributors = df['Author'].unique()

        def generate_contributors():
            global commit_amounts
            global commit_avg_contribution
            global avg_cont
            for x in df_contributors:
                for commit in Repository(path_global, only_authors=[x]).traverse_commits():
                    for file in commit.modified_files:
                        commit_amounts += 1

                if not (commit_amounts == 0) and not (avg_cont == 0):
                    avg_cont_total = avg_cont / len(df_contributors)
                    total_avg = commit_amounts / avg_cont
                    perc_cont = total_avg * 100
                    contributors_stats.append({'Author': x, 'Commit Amounts': commit_amounts,
                                               'Percentage Contribution': perc_cont, 'Average Contribution': avg_cont_total})
                else:
                    contributors_stats.append({'Author': x, 'Commit Amounts': commit_amounts,
                                               'Percentage Contribution': 0, 'Average Contribution': 0})

                commit_amounts = 0

        generate_contributors()

        df_contributors_stats = pd.DataFrame(contributors_stats)

        repository_max_added_lines_commit = get_highest(df['Insertions'])
        repository_avg_added_lines_commit = get_avg(df['Insertions'])
        repository_max_deletions_in_commit = get_highest(df['Deletions'])
        repository_avg_deleted_lines_commit = get_avg(df['Deletions'])

        project_stats = [[repository_max_added_lines_commit,
                          repository_avg_added_lines_commit, repository_max_deletions_in_commit,
                          repository_avg_deleted_lines_commit]]

        columns = ['Max Lines Added', 'Average Added Lines',
                   'Max Lines Deleted', 'Average Lines Deleted']

        df_project_stats = pd.DataFrame(project_stats, columns=columns)

        def generate_csv():
            df.to_csv(
                f'./data/{repo_name}{date_from}{date_to}/info_commits.csv', index=False)
            df_two.to_csv(
                f'./data/{repo_name}{date_from}{date_to}/modifications_info.csv', index=False)
            df_contributors_stats.to_csv(
                f'./data/{repo_name}{date_from}{date_to}/stats_contributors.csv', index=False)
            df_project_stats.to_csv(
                f'./data/{repo_name}{date_from}{date_to}/stats_repository.csv', index=False)

        generate_csv()
        valid = True
        return valid

    except:
        return valid
