import os
import subprocess
import datetime
import pathlib
from typing import Final

n_repeat: Final = 1

ex_cnt: Final = 14


def time_licorne():
    results = []
    with open("./timing-runs/licorne/stdout.txt", "w+") as stdout:
        with open("./timing-runs/licorne/stderr.txt", "w+") as stderr:
            for i in range(0, n_repeat):
                total_delta = 0
                cnt = 0
                for ex in os.listdir("../licorne"):
                    if ex == "temp" or not pathlib.Path("../licorne/" + ex).is_dir():
                        continue
                    cmd = f"java -jar licorne-compiler.jar compile {ex}"
                    local_start = datetime.datetime.now()
                    subprocess.run(cmd, cwd="../licorne", shell=True, stdout=stdout, stderr=stderr)
                    local_delta = datetime.timedelta.total_seconds(datetime.datetime.now() - local_start)
                    total_delta += local_delta
                    cnt += 1
                    print(f"{i} - Licorne {ex} : {local_delta}")
                results.append(total_delta)
                assert cnt == ex_cnt, f"found {cnt} examples"
    print("\nLicorne")
    with open("./timing-runs/licorne/times.txt", "w+") as times_file:
        for res in results:
            times_file.write(f"{res}\n")
            print(res)
    print("\n")


def time_scala():
    results = []
    with open("./timing-runs/scala/stdout.txt", "w+") as stdout:
        with open("./timing-runs/scala/stderr.txt", "w+") as stderr:
            for i in range(0, n_repeat):
                total_delta = 0
                cnt = 0
                for ex in os.listdir("../scala/src/main/scala"):
                    files = os.listdir("../scala/src/main/scala/" + ex)
                    files = [f"../scala/src/main/scala/{ex}/{f}" for f in files if f.endswith(".scala")]
                    cmd = "scalac -Ystop-after:typer " + " ".join(files)
                    local_start = datetime.datetime.now()
                    subprocess.run(cmd, cwd="../scala", shell=True, stdout=stdout, stderr=stderr)
                    local_delta = datetime.timedelta.total_seconds(datetime.datetime.now() - local_start)
                    cnt += 1
                    print(f"{i} - Scala {ex} : {local_delta}")
                    total_delta += local_delta
                results.append(total_delta)
                assert cnt == ex_cnt, f"found {cnt} examples"
    print("\nScala")
    with open("./timing-runs/scala/times.txt", "w+") as times_file:
        for res in results:
            times_file.write(f"{res}\n")
            print(res)
    print("\n")


if __name__ == "__main__":
    pathlib.Path("./timing-runs/licorne").mkdir(parents=True, exist_ok=True)
    pathlib.Path("./timing-runs/scala").mkdir(parents=True, exist_ok=True)
    time_licorne()
    time_scala()
