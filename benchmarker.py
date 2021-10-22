import subprocess

# modes to benchmark
# 0 = Testing
# 2 = Long Optimized
# 3 = IntOptimized
modes = [0, 2, 3]

options = "9 10_000_009 true 10"

for x in range(0,3):
	outputs = [[], [], []]

	for j in range(0, 5):
		for i in range(0, len(modes)):
			p = subprocess.Popen("java -cp D:\programming\VScode\java\school\Solver\Solver\\bin ch.lsh.solver.main.Main " +
								str(modes[i]) + " " + options, stdout=subprocess.PIPE, shell=True)
			(out, err) = p.communicate()
			p.wait()
			out = out.splitlines()
			ns = int(out[len(out)-6].decode("UTF-8").replace("ns", ""))
			outputs[i].append(ns)

	print("Finished collecting data...")
			
	final_times_ns = []

	for i in range(0, len(outputs)):
		ns = 0
		for time in outputs[i]:
			ns += time
		final_times_ns.append(ns/len(outputs[i]))

	print(f"{final_times_ns[0]/1000} testing")
	print(f"{final_times_ns[1]/1000} long-optimzed")
	print(f"{final_times_ns[2]/1000} int-optimized")